package com.theapache64.ghmm.templates.runningawayballoon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RunningAwayBalloonData(
    @SerialName("balloon_text")
    val balloonText: String, // TEXT_1_GOES_HERE
    @SerialName("font_size")
    val fontSize: Float, // 54
    @SerialName("pink_person")
    val pinkPerson: String, // TEXT_2_GOES_HERE
    @SerialName("grey_person")
    val greyPerson: String, // TEXT_3_GOES_HERE
    @SerialName("template_id")
    val templateId: String // running-away-balloon-meme
)