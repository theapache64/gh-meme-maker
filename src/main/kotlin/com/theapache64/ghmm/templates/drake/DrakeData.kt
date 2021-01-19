package com.theapache64.ghmm.templates.drake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrakeData(
    @SerialName("text_1")
    var text1: String, // Dagger2
    @SerialName("text_2")
    val text2: String, // Hilt
    @SerialName("font_size")
    val fontSize: Float = 60f
)