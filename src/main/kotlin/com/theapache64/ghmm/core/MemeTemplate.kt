package com.theapache64.ghmm.core

import com.theapache64.ghmm.util.text.TextAlignment
import com.theapache64.ghmm.util.text.TextFormat
import com.theapache64.ghmm.util.text.TextRenderer
import java.awt.*
import java.io.File
import javax.imageio.ImageIO

abstract class MemeTemplate<T>(
    private val isDebug: Boolean
) {
    companion object {
        val fontImpact: Font by lazy {
            Font.createFont(Font.TRUETYPE_FONT, File("impact.ttf"))
        }
        val TRANSPARENT: Color = Color.decode("#00000000")!!
    }

    abstract fun getId(): String
    abstract fun getTemplateImageName(): String
    open fun isNeedDynamicTextBgColor() = false

    fun generate(jsonStringData: String): File {

        val templateImageFile = createTempTemplateFile()

        // Preparing canvas
        val input = ImageIO.read(templateImageFile)
        val canvas = input.createGraphics()
        canvas.setRenderingHint(
            RenderingHints.KEY_FRACTIONALMETRICS,
            RenderingHints.VALUE_FRACTIONALMETRICS_ON
        );
        canvas.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB
        );

        // Drawing
        onCanvasReady(canvas, jsonStringData)

        // Creating final image
        canvas.dispose()
        ImageIO.write(input, templateImageFile.extension, templateImageFile)

        return templateImageFile
    }

    private fun onCanvasReady(canvas: Graphics2D, jsonStringData: String) {
        val data = decodeJson(jsonStringData)
        val font = fontImpact.deriveFont(Font.PLAIN, getFontSize(data))
        val textData: List<Pair<String, Rectangle>> = getTextCoordinates(data)
        println(textData)
        for ((text, boundary) in textData) {
            draw(canvas, font, text, boundary)
        }
    }

    abstract fun getFontSize(data: T): Float
    private fun draw(canvas: Graphics2D, font: Font, text1: String, text1Bounds: Rectangle) {
        val textBgColor = if (isNeedDynamicTextBgColor()) Color.WHITE else null
        TextRenderer.drawString(
            canvas,
            text1,
            textBgColor,
            font,
            Color.decode("#121212"),
            text1Bounds,
            TextAlignment.MIDDLE,
            TextFormat.FIRST_LINE_VISIBLE
        )
    }

    /**
     * To create temporary template file from original source image
     */
    private fun createTempTemplateFile(): File {
        val templateImageFile = File("template_images/${getTemplateImageName()}")
        val uniqueText = if (isDebug) {
            ""
        } else {
            "_${System.nanoTime()}"
        }
        val temporaryImageFile =
            File("temp/${templateImageFile.nameWithoutExtension}${uniqueText}.${templateImageFile.extension}")
        templateImageFile.copyTo(temporaryImageFile, overwrite = true)
        return temporaryImageFile
    }

    abstract fun decodeJson(jsonStringData: String): T

    abstract fun getTextCoordinates(data: T): List<Pair<String, Rectangle>>
}