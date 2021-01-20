package com.theapache64.ghmm.templates.mask

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MaskData(
    @SerialName("mask_text")
    var maskText: String,
    @SerialName("revealed_text")
    val revealedText: String,
    @SerialName("font_size")
    val fontSize: Float = 50f,
)
