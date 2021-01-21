package com.theapache64.ghmm.templates

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BaseData(
    @SerialName("template_id")
    val templateId: String
)