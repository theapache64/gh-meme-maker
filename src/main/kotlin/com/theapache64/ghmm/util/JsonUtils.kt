package com.theapache64.ghmm.util

import kotlinx.serialization.json.Json

object JsonUtils {
    val json = Json {
        ignoreUnknownKeys = true
    }
}