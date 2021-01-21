package com.theapache64.ghmm.templates.fmburns

import com.theapache64.expekt.should
import com.theapache64.ghmm.templates.drake.DrakeMemeTemplate
import org.junit.Test

class FiremanBurnsMemeTemplateTest {

    @Test
    fun `Generate fireman burns meme`() {

        val inputData = """
                {
                  "lady_says": "Fragments are amazing!",
                  "font_size": 30
                }
            """.trimIndent()

        val outputFile = FiremanBurnsMemeTemplate(true)
            .generate(inputData)

        // Checking file existence
        outputFile.exists().should.`true`
    }
}