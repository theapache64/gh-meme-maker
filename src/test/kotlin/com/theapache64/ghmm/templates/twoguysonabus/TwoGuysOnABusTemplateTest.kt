package com.theapache64.ghmm.templates.twoguysonabus

import com.theapache64.expekt.should
import com.theapache64.ghmm.templates.runningawayballoon.RunningAwayBalloonTemplate
import org.junit.Test

class TwoGuysOnABusTemplateTest {
    @Test
    fun `Generate two guys on a bus meme`() {

        val inputData = """
                {
                  "template_id": "two-guys-on-a-bus-meme",
                  "sad_guy": "Java",
                  "happy_guy": "Kotlin",
                  "font_size": 40
                }
            """.trimIndent()

        val outputFile = TwoGuysOnABusTemplate(true)
            .generate(inputData)

        // Checking file existence
        outputFile.exists().should.`true`
    }
}