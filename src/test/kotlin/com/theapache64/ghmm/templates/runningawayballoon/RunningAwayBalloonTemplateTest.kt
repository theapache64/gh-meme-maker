package com.theapache64.ghmm.templates.runningawayballoon

import com.theapache64.expekt.should
import org.junit.Test

class RunningAwayBalloonTemplateTest {
    @Test
    fun `Generate running away balloon template meme`() {

        val inputData = """
                {
                  "template_id": "running-away-balloon-meme",
                  "balloon_text": "TEXT_1_GOES_HERE",
                  "pink_person": "TEXT_2_GOES_HERE",
                  "grey_person":"Me",
                  "font_size": 40
                }
            """.trimIndent()

        val outputFile = RunningAwayBalloonTemplate(true)
            .generate(inputData)

        // Checking file existence
        outputFile.exists().should.`true`
    }
}