package com.theapache64.ghmm.templates.drake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DrakeData(
    @SerialName("drake_hates")
    var drakeHates: String, // Dagger2
    @SerialName("drake_loves")
    val drakeLoves: String, // Hilt
    @SerialName("font_size")
    val fontSize: Float = 80f,
)
