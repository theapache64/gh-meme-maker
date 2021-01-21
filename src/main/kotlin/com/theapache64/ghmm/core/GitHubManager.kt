package com.theapache64.ghmm.core

import com.theapache64.ghmm.model.github.CreateIssueCommentBody
import com.theapache64.ghmm.model.github.IssueComment
import com.theapache64.ghmm.util.JsonUtils
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * To manage all GitHub operations
 */
object GitHubManager {
    private const val REPO = "theapache64/gh-meme-maker"

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val githubOAuthToken by lazy {
        System.getenv("GITHUB_OAUTH")
    }


    private val jsonMediaType by lazy {
        "application/json; charset=utf-8".toMediaType()
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
            return JsonUtils.json.decodeFromString<IssueComment>(resp).body
        }
        return null
    }

    /**
     * To get issue body from issueNumber
     */
    private fun getIssueBody(issueNumber: Long): String? {
        val request = Request.Builder()
            .url("https://api.github.com/repos/$REPO/issues/$issueNumber")
            .build()

        val jsonResp = okHttpClient.newCall(request).execute().body?.string()
        return if (jsonResp != null) {
            return JsonUtils.json.decodeFromString<IssueComment>(jsonResp).body
        } else {
            null
        }
    }

    /**
     * To create a new comment in the given issue
     */
    fun createComment(_body: String, issueNumber: Long): Boolean {


        val body = JsonUtils.json.encodeToString(CreateIssueCommentBody(_body))

        val request = Request.Builder()
            .url("https://api.github.com/repos/$REPO/issues/$issueNumber/comments")
            .addHeader("Accept", "application/vnd.github.v3+json")
            .addHeader("authorization", "token $githubOAuthToken")
            .method("POST", body.toRequestBody(jsonMediaType))
            .build()

        val result = okHttpClient.newCall(request).execute()
        println(result.body?.string())
        return result.code == 201
    }

}