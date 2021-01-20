package com.theapache64.ghmm.templates.drake

import com.theapache64.ghmm.core.MemeTemplate
import com.theapache64.ghmm.util.JsonUtils
import com.theapache64.ghmm.util.text.TextAlignment
import com.theapache64.ghmm.util.text.TextFormat
import com.theapache64.ghmm.util.text.TextRenderer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.awt.*
import java.io.File

class DrakeMemeTemplate(
    isDebug: Boolean = false
) : MemeTemplate(isDebug) {

    companion object {
        private val fontImpact by lazy {
            Font.createFont(Font.TRUETYPE_FONT, File("impact.ttf"))
        }
    }

    override fun getId(): String {
        return "drake-meme"
    }

    override fun getTemplateImageName(): String {
        return "drake.jpg"
    }

    override fun onCanvasReady(canvas: Graphics2D, jsonStringData: String) {
        val drakeData: DrakeData = JsonUtils.json.decodeFromString(jsonStringData)
        val font = fontImpact.deriveFont(Font.PLAIN, drakeData.fontSize)

        val text1Bounds = Rectangle(500, 0, 500, 500)
        draw(canvas, font, drakeData.text1, text1Bounds)

        val text2Bounds = Rectangle(500, 500, 500, 500)
        draw(canvas, font, drakeData.text2, text2Bounds)
    }

    private fun draw(canvas: Graphics2D, font: Font, text1: String, text1Bounds: Rectangle) {
        TextRenderer.drawString(
            canvas,
            text1,
            font,
            Color.BLACK,
            text1Bounds,
            TextAlignment.MIDDLE,
            TextFormat.FIRST_LINE_VISIBLE
        )
    }

}