package com.theapache64.ghmm

import com.theapache64.ghmm.core.GitHubManager
import com.theapache64.ghmm.core.ImgUr
import com.theapache64.ghmm.core.TemplateManager
import com.theapache64.ghmm.templates.BaseData
import com.theapache64.ghmm.util.JsonUtils
import kotlinx.serialization.decodeFromString
import kotlin.system.exitProcess

const val SIGNATURE = "GjhgJHGJHGjHGjhgjGJ4545JjHGjhgjhg"

fun main(args: Array<String>) {
    println("Generating meme : [${args.joinToString(",")}]")

    // Getting input from arguments
    val issueNumber = args[1].toLong()
    val commentId = args[3].let { arg3 ->
        if (arg3.isBlank()) {
            null
        } else {
            arg3.toLong()
        }
    }

    println("Issue Number : `${issueNumber}`")
    println("Comment ID : `$commentId`")

    try {

        // Getting issue/comment body
        val body = GitHubManager.getBody(
            issueNumber,
            commentId
        )

        if (body != null) {
            if (body.contains("```json") && !body.contains(SIGNATURE)) {

                val bodyJson = parseJsonFromMarkdownIssueBody(body)
                val bodyModel: BaseData = JsonUtils.json.decodeFromString(bodyJson)

                // Asking manager to provide correct template
                val template = TemplateManager.getTemplate(bodyModel.templateId)

                if (template != null) {

                    // Asking template to generate meme
                    println("Template: ${template::class.java.simpleName}")
                    val imageFile = template.generate(bodyJson)
                    val imageUrl = ImgUr.uploadImage(imageFile)

                    if (imageUrl != null) {

                        // Delete uploaded image
                        imageFile.delete()

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

                        // Creating comment
                        val isCommentCreated = GitHubManager.createComment(
                            comment,
                            issueNumber
                        )

                        println("Done! -> $isCommentCreated")
                    } else {
                        error("Failed to upload image to imgUr")
                    }
                } else {
                    error("Invalid template id ${bodyModel.templateId}")
                }
            } else {
                println("It's not a generate command. Skippink call.")
                exitProcess(0)
            }
        } else {
            error("Invalid issue/comment : IssueNum-> '$issueNumber', CommentId: '$commentId'")
        }
    } catch (e: Exception) {
        e.printStackTrace()
        GitHubManager.createComment(
            e.message ?: "Something went wrong",
            issueNumber
        )
        throw e
    }
}

fun parseJsonFromMarkdownIssueBody(body: String): String {
    body.split("```json")[1].let { x ->
        return x.substring(0, x.lastIndexOf("```"))
    }
}

