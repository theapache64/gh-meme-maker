package com.theapache64.ghmm.templates.drake

import com.theapache64.expekt.should
import org.junit.Test


class DrakeMemeTemplateTest {
    @Test
    fun `Generate drake meme`() {

        val inputData = """
                {
                  "text_1": "Dagger",
                  "text_2": "Hilt"
                }
            """.trimIndent()

        val outputFile = DrakeMemeTemplate(true)
            .generate(inputData)

        // Checking file existence
        outputFile.exists().should.`true`
    }
}