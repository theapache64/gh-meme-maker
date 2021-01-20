package com.theapache64.ghmm.templates.mask

import com.theapache64.expekt.should
import org.junit.Test


class MaskMemeTemplateTest {
    @Test
    fun `Generate mask meme`() {

        val inputData = """
                {
                  "mask_text": "Hilt",
                  "revealed_text": "Dagger"
                }
            """.trimIndent()

        val outputFile = MaskMemeTemplate(true)
            .generate(inputData)

        // Checking file existence
        outputFile.exists().should.`true`
    }
}