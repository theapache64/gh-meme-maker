package com.theapache64.ghmm.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubIssue(
    @SerialName("body")
    val body: String // I'm having a problem with this.
)