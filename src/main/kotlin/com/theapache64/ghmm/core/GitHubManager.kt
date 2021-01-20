package com.theapache64.ghmm.core

import com.theapache64.ghmm.model.GithubIssueComment
import com.theapache64.ghmm.util.JsonUtils
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import org.kohsuke.github.GitHubBuilder

/**
 * To manage all GitHub operations
 */
object GitHubManager {
    private const val REPO = "theapache64/gh-meme-maker"

    private val okHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val github by lazy {
        GitHubBuilder.fromEnvironment().build()
    }

    /**
     * To get comment body of an issue/comment
     */
    fun getBody(issueNumber: Long, commentId: Long?): String? {
        return if (commentId == null) {
            // It's a new issue
            getIssueBody(issueNumber)
        } else {
            // It's a new comment
            getCommentBody(commentId)
        }
    }

    /**
     * To get comment body from commentId
     */
    private fun getCommentBody(commentId: Long): String? {

        // Not using GitHub Java API, because its slow.
        val request = Request.Builder()
            .addHeader("Accept", "application/vnd.github.v3+json")
            .url("https://api.github.com/repos/$REPO/issues/comments/$commentId")
            .build()

        val resp = okHttpClient.newCall(request).execute().body?.string()
        if (resp != null) {
            return JsonUtils.json.decodeFromString<GithubIssueComment>(resp).body
        }
        return null
    }

    /**
     * To get issue body from issueNumber
     */
    private fun getIssueBody(issueNumber: Long): String {
        return github.getRepository(REPO)
            .getIssue(issueNumber.toInt())
            .body
    }

    /**
     * To create a new comment in the given issue
     */
    fun createComment(body: String, issueNumber: Long) {
        github.getRepository(REPO)
            .getIssue(issueNumber.toInt())
            .comment(body)
    }

}