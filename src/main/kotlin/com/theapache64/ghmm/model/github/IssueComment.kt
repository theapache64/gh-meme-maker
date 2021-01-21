package com.theapache64.ghmm.model.github

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IssueComment(
    @SerialName("body")
    val body: String
)