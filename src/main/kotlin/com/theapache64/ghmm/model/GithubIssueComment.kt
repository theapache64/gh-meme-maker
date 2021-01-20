package com.theapache64.ghmm.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GithubIssueComment(
    @SerialName("body")
    val body: String // dfgdfg
)