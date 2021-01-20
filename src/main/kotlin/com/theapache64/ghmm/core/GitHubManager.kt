package com.theapache64.ghmm.core

import com.theapache64.ghmm.model.GithubIssueComment
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import org.kohsuke.github.GitHubBuilder

object GitHubManager {
    private const val REPO = "theapache64/gh-meme-maker"

    private val okHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val github by lazy {
        GitHubBuilder.fromEnvironment().build()
    }

    fun getBody(issueNumber: Long, commentId: Long?): String? {
        return if (commentId == null) {
            // It's a new issue
            getIssueBody(issueNumber)
        } else {
            // It's a new comment
            getCommentBody(issueNumber, commentId)
        }
    }

    private fun getCommentBody(issueNumber: Long, commentId: Long): String? {
        val request = Request.Builder()
            .addHeader("Accept", "application/vnd.github.v3+json")
            .url("https://api.github.com/repos/$REPO/issues/comments/$commentId")
            .build()

        val resp = okHttpClient.newCall(request).execute().body?.string()
        if (resp != null) {
            return Json {
                ignoreUnknownKeys = true
            }.decodeFromString<GithubIssueComment>(resp).body
        }
        return null
    }

    private fun getIssueBody(issueNumber: Long): String {
        return github.getRepository(REPO)
            .getIssue(issueNumber.toInt())
            .body
    }

    fun createComment(body: String, issueNumber: Long, commentId: Long?) {
        github.getRepository(REPO)
            .getIssue(issueNumber.toInt())
            .comment(body)
    }

}