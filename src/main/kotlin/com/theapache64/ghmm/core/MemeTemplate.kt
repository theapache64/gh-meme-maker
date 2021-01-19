package com.theapache64.ghmm.core

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.awt.Color
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.io.File
import javax.imageio.ImageIO

abstract class MemeTemplate(
    private val isDebug: Boolean
) {
    abstract fun getId(): Int
    abstract fun getTemplateImageName(): String

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

    abstract fun onCanvasReady(canvas: Graphics2D, jsonStringData: String)

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
}