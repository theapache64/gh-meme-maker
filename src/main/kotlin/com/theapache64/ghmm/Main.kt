package com.theapache64.ghmm

import com.theapache64.ghmm.core.GitHubManager
import com.theapache64.ghmm.core.TemplateManager
import com.theapache64.ghmm.templates.BaseData
import com.theapache64.ghmm.util.JsonUtils
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.lang.Exception
import kotlin.system.exitProcess

private val requestReceivedReplies = listOf(
    "Alrightyyy!! Let me generate it...",
    "Roger that! Hold on please",
    "Copy that. Hold on a minute"
)

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
            if (body.startsWith("```json")) {

                // Sending 'Please wait' message
                GitHubManager.createComment(
                    requestReceivedReplies.random(),
                    issueNumber
                )

                val bodyJson = parseJsonFromMarkdownIssueBody(body)
                val bodyModel: BaseData = JsonUtils.json.decodeFromString(bodyJson)

                // Asking manager to provide correct template
                val template = TemplateManager.getTemplate(bodyModel.templateId)

                if (template != null) {

                    // Asking template to generate meme
                    println("Template: ${template::class.java.simpleName}")
                    val imageFile = template.generate(bodyJson)
                    val comment = String.format(
                        """
                        Here is it ;)
                        
                        <img src="https://raw.githubusercontent.com/theapache64/gh-meme-maker/storage/${imageFile.name}" width="300"/>
                        
                        > If you're seeing a broken image, come back after a minute. GitHub is hosting your meme...
                        
                        Didn't like the output? Don't worry, copy paste below JSON with updated values. I can make a new one.
                        ````json
                        %s
                        ````
                    """.trimIndent(), body
                    )

                    // Creating comment
                    GitHubManager.createComment(
                        comment,
                        issueNumber
                    )

                    println("Done!")
                } else {
                    error("Invalid template id ${bodyModel.templateId}")
                }
            }else{
                println("It's not a generate command. Skipping call.")
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

