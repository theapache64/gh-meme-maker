package com.theapache64.ghmm.core

import com.theapache64.expekt.should
import com.theapache64.ghmm.SIGNATURE
import org.junit.Test

class GitHubManagerTest {
    @Test
    fun `Get issue body`() {
        GitHubManager.getBody(3, null)
            ?.length.should.above(10)
    }

    @Test
    fun `Get Comment body`() {
        GitHubManager.getBody(1, 763282001)
            ?.length.should.equal(6)
    }

    @Test
    fun `Create comment`() {

        GitHubManager.createComment(
            "This is sample comment",
            29
        ).should.`true`
    }

    @Test
    fun `Create big comment`() {
        val imageUrl = "https://i.imgur.com/fZfB4P0.jpg"
        val bodyJson = """
            
            {
              "template_id": "fireman-burns-meme",
              "lady_says": "I am a Flutter Developer",
              "font_size": 30
            }
            
        """.trimIndent()
        val comment = String.format(
            """
                        Here is it ;)
                        
                        <img src="$imageUrl" width="300"/>

                        Didn't like the output? Don't worry, copy paste below JSON with updated values. I can make a new one.
                        ````json
                        ```json%s```
                        ````
                        <!--
                        $SIGNATURE
                        -->
                    """.trimIndent(), bodyJson
        )

        GitHubManager.createComment(
            comment,
            29
        ).should.`true`
    }
}