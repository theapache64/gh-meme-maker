package com.theapache64.ghmm.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubIssueComment(
    @SerialName("body")
    val body: String
)