package com.theapache64.ghmm.model

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class ImgUrSuccessResponse(
    @SerialName("data")
    val `data`: Data?,
    @SerialName("status")
    val status: Int, // 200
    @SerialName("success")
    val success: Boolean // true
) {
    @Serializable
    data class Data(
        @SerialName("link")
        val link: String // https://i.imgur.com/M8EKJ4H.jpg
    )
}