package com.theapache64.ghmm.templates.expandingbrain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ExpandingBrainData(
    @SerialName("level_1")
    val level1: String,
    @SerialName("level_2")
    val level2: String,
    @SerialName("level_3")
    val level3: String,
    @SerialName("level_4")
    val level4: String,
    @SerialName("font_size")
    val fontSize: Float = 50f,
)