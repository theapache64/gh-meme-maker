package com.theapache64.ghmm.templates

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class BaseData(
    @SerialName("template_id")
    val templateId: String
)