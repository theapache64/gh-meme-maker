package com.theapache64.ghmm.templates.fmburns

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class FiremanBurnsData(
    @SerialName("lady_says")
    val ladySays: String, // TEXT_GOES_HERE
    @SerialName("font_size")
    val fontSize: Float = 30f // 80
)