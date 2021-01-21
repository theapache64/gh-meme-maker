package com.theapache64.ghmm.model.github

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Issue(
    @SerialName("body")
    val body: String // I'm having a problem with this.
)