package com.theapache64.ghmm

import com.theapache64.ghmm.core.GitHubManager
import com.theapache64.ghmm.core.TemplateManager
import com.theapache64.ghmm.templates.BaseData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.lang.Exception

private val requestReceivedReplies = listOf(
    "Alrightyyy!! Let me generate it...",
    "Roger that! Hold on please",
    "Copy that. Hold on a minute"
)

fun main(args: Array<String>) {
    println("Generating meme : [${args.joinToString(",")}]")

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

                val bodyJson = parseJsonFrom(body)
                val bodyModel: BaseData = Json {
                    ignoreUnknownKeys = true
                }.decodeFromString(bodyJson)

                // Asking manager to provide correct template
                val template = TemplateManager.getTemplate(bodyModel.templateId)

                if (template != null) {

                    // Asking template to generate meme
                    println("Template: ${template::class.java.simpleName}")
                    val imageFile = template.generate(bodyJson)

                    GitHubManager.createComment(
                        """Here is it 😜, 
                        <img src="https://raw.githubusercontent.com/theapache64/gh-meme-maker/storage/${imageFile.name}" width="300"/>
                        
                        
                    """.trimIndent(),
                        issueNumber
                    )
                    println("Done!")
                } else {
                    error("Invalid template id ${bodyModel.templateId}")
                }
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

fun parseJsonFrom(body: String): String {
    body.split("```json")[1].let { x ->
        return x.substring(0, x.lastIndexOf("```"))
    }
}

