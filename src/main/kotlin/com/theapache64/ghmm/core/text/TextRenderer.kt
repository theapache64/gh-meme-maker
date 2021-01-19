package com.theapache64.ghmm.core.text

import com.theapache64.ghmm.core.text.TextFormat.isEnabled
import java.awt.*
import java.awt.font.FontRenderContext
import java.awt.font.LineBreakMeasurer
import java.awt.font.TextAttribute
import java.text.AttributedString

/**
 * A class which provides static methods for rendering text using alignment.
 */
object TextRenderer {

    @JvmOverloads
    fun drawString(
        g: Graphics,
        text: String,
        font: Font,
        color: Color,
        bounds: Rectangle,
        align: TextAlignment = TextAlignment.TOP_LEFT,
        format: Int = TextFormat.NONE
    ): Rectangle {

        if (text.isEmpty()) {
            return Rectangle(bounds.x, bounds.y, 0, 0)
        }

        val g2D = g as Graphics2D
        val attributedString = AttributedString(text).apply {
            addAttribute(TextAttribute.FOREGROUND, color)
            addAttribute(TextAttribute.FONT, font)
        }
        val attributedCharIterator = attributedString.iterator
        val fontContext = FontRenderContext(null, !isEnabled(format, TextFormat.NO_ANTI_ALIASING), false)
        val lineMeasurer = LineBreakMeasurer(attributedCharIterator, fontContext)
        val targetLocation = Point(bounds.x, bounds.y)
        var nextOffset: Int
        if (align.isMiddle || align.isBottom) {
            if (align.isMiddle) targetLocation.y = bounds.y + bounds.height / 2
            if (align.isBottom) targetLocation.y = bounds.y + bounds.height
            while (lineMeasurer.position < text.length) {
                nextOffset = lineMeasurer.nextOffset(bounds.width.toFloat())
                nextOffset = nextTextIndex(nextOffset, lineMeasurer.position, text)
                val textLayout = lineMeasurer.nextLayout(bounds.width.toFloat(), nextOffset, false)
                if (align.isMiddle) {
                    targetLocation.y -= ((textLayout.ascent + textLayout.leading + textLayout.descent) / 2).toInt()
                }
                if (align.isBottom) {
                    targetLocation.y -= (textLayout.ascent + textLayout.leading + textLayout.descent).toInt()
                }
            }
            if (isEnabled(format, TextFormat.FIRST_LINE_VISIBLE)) targetLocation.y = Math.max(0, targetLocation.y)
            lineMeasurer.position = 0
        }
        if (align.isRight || align.isCenter) targetLocation.x = bounds.x + bounds.width
        val consumedBounds = Rectangle(targetLocation.x, targetLocation.y, 0, 0)
        while (lineMeasurer.position < text.length) {
            nextOffset = lineMeasurer.nextOffset(bounds.width.toFloat())
            nextOffset = nextTextIndex(nextOffset, lineMeasurer.position, text)
            val textLayout = lineMeasurer.nextLayout(bounds.width.toFloat(), nextOffset, false)
            val textBounds = textLayout.bounds
            targetLocation.y += textLayout.ascent.toInt()
            consumedBounds.width = consumedBounds.width.coerceAtLeast(textBounds.width.toInt())
            when (align) {
                TextAlignment.TOP_LEFT, TextAlignment.MIDDLE_LEFT, TextAlignment.BOTTOM_LEFT -> textLayout.draw(
                    g2D,
                    targetLocation.x.toFloat(),
                    targetLocation.y.toFloat()
                )
                TextAlignment.TOP, TextAlignment.MIDDLE, TextAlignment.BOTTOM -> {
                    targetLocation.x = bounds.x + bounds.width / 2 - (textBounds.width / 2).toInt()
                    consumedBounds.x = consumedBounds.x.coerceAtMost(targetLocation.x)
                    textLayout.draw(g2D, targetLocation.x.toFloat(), targetLocation.y.toFloat())
                }
                TextAlignment.TOP_RIGHT, TextAlignment.MIDDLE_RIGHT, TextAlignment.BOTTOM_RIGHT -> {
                    targetLocation.x = bounds.x + bounds.width - textBounds.width.toInt()
                    textLayout.draw(g2D, targetLocation.x.toFloat(), targetLocation.y.toFloat())
                    consumedBounds.x = consumedBounds.x.coerceAtMost(targetLocation.x)
                }
            }
            targetLocation.y += (textLayout.leading + textLayout.descent).toInt()
        }
        consumedBounds.height = targetLocation.y - consumedBounds.y
        return consumedBounds
    }

    private fun nextTextIndex(nextOffset: Int, measurerPosition: Int, text: String): Int {
        for (i in measurerPosition + 1 until nextOffset) {
            if (text[i] == '\n') return i
        }
        return nextOffset
    }
}