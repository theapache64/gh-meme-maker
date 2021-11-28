package com.theapache64.ghmm.templates.twoguysonabus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TwoGuysOnABusData(
    @SerialName("font_size")
    val fontSize: Float, // 50
    @SerialName("happy_guy")
    val happyGuy: String, // HAPPY_TEXT_GOES_HERE
    @SerialName("sad_guy")
    val sadGuy: String, // SAD_TEXT_GOES_HERE
    @SerialName("template_id")
    val templateId: String // two-guys-on-a-bus-meme
)