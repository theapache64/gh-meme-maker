package com.theapache64.ghmm.templates.firemanburns

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FiremanBurnsData(
    @SerialName("lady_says")
    val ladySays: String, // TEXT_GOES_HERE
    @SerialName("font_size")
    val fontSize: Float = 30f // 80
)