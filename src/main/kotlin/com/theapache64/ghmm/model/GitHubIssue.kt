package com.theapache64.ghmm.model

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class GitHubIssue(
    @SerialName("body")
    val body: String // I'm having a problem with this.
)