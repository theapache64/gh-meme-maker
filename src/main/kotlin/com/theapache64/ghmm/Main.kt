package com.theapache64.ghmm

import com.theapache64.ghmm.core.GitHubManager
import com.theapache64.ghmm.core.TemplateManager
import com.theapache64.ghmm.templates.BaseData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.kohsuke.github.GitHub

fun main(args: Array<String>) {

    val issueNumber = args[1].toLong()
    val commentId = args[3].let { arg3 ->
        if (arg3.isBlank()) {
            null
        } else {
            arg3.toLong()
        }
    }

    val body = GitHubManager.getBody(
        issueNumber,
        commentId
    )

    if (body != null) {
        val bodyJson = parseJsonFrom(body)
        val bodyModel: BaseData = Json {
            ignoreUnknownKeys = true
        }.decodeFromString(bodyJson)

        if (args.size == 4) {

            // Asking manager to provide correct template
            val template = TemplateManager.getTemplate(bodyModel.templateId)

            if (template != null) {

                // Asking template to generate meme
                println("Template: ${template::class.java.simpleName}")
                val imageFile = template.generate(bodyJson)

                GitHubManager.createComment(
                    """
                        ![](https://raw.githubusercontent.com/theapache64/gh-meme-maker/storage/${imageFile.name})
                    """.trimIndent(),
                    issueNumber,
                    commentId
                )
            } else {
                error("Invalid template id ${bodyModel.templateId}")
            }
        } else {
            error("Invalid argument size '${args.size}'")
        }
    } else {
        error("Invalid issue/comment : IssueNum-> '$issueNumber', CommentId: '$commentId'")
    }
}

fun parseJsonFrom(body: String): String {
    body.split("```json")[1].let { x ->
        return x.substring(0, x.lastIndexOf("```"))
    }
}

