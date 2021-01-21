package com.theapache64.ghmm.model.github

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CreateIssueCommentBody(
    @SerialName("body")
    val body: String // something
)