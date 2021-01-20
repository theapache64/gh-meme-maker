package com.theapache64.ghmm.templates.drake

import com.theapache64.ghmm.templates.BaseData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DrakeData(
    @SerialName("text_1")
    var text1: String, // Dagger2
    @SerialName("text_2")
    val text2: String, // Hilt
    @SerialName("font_size")
    val fontSize: Float = 60f,
)
