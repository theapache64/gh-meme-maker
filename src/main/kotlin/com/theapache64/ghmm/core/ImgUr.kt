package com.theapache64.ghmm.core

import com.theapache64.ghmm.model.ImgUrSuccessResponse
import com.theapache64.ghmm.util.JsonUtils
import kotlinx.serialization.decodeFromString
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.util.*

object ImgUr {

    private val okHttpClient by lazy {
        OkHttpClient()
    }

    private val imgUrClientId: String by lazy {
        System.getenv("IMGUR_CLIENT_ID")
    }

    fun uploadImage(
        imageFile: File
    ): String? {
        val base64Image = Base64.getEncoder().encodeToString(imageFile.readBytes())

        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("image", base64Image)
            .addFormDataPart("type", "base64")
            .build()

        val request = Request.Builder()
            .url("https://api.imgur.com/3/image")
            .addHeader("Authorization", "Client-ID: $imgUrClientId")
            .post(body)
            .build()

        val result = okHttpClient.newCall(request).execute().also {
            println("ErrorCode: ${it.code}")
        }
        if (result.code == 200) {
            val responseJson = result.body?.string()
            if (responseJson != null) {
                val response = JsonUtils.json.decodeFromString<ImgUrSuccessResponse>(responseJson)
                if (response.success) {
                    return response.data?.link
                }
            }
        }
        return null
    }
}