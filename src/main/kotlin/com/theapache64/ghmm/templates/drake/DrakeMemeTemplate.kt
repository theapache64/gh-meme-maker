package com.theapache64.ghmm.templates.drake

import com.theapache64.ghmm.core.MemeTemplate
import com.theapache64.ghmm.util.JsonUtils
import kotlinx.serialization.decodeFromString
import java.awt.*

class DrakeMemeTemplate(
    isDebug: Boolean = false
) : MemeTemplate<DrakeData>(isDebug) {


    override fun getId(): String {
        return "drake-meme"
    }

    override fun getTemplateImageName(): String {
        return "drake.jpg"
    }

    override fun getFontSize(data: DrakeData): Float {
        return data.fontSize
    }

    override fun decodeJson(jsonStringData: String): DrakeData {
        return JsonUtils.json.decodeFromString(jsonStringData)
    }

    override fun getTextCoordinates(drakeData: DrakeData): List<Pair<String, Rectangle>> {
        return mutableListOf(
            drakeData.text1 to Rectangle(500, 0, 500, 500),
            drakeData.text2 to Rectangle(500, 500, 500, 500)
        )
    }
}