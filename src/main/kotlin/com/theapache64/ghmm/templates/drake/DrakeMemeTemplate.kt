package com.theapache64.ghmm.templates.drake

import com.theapache64.ghmm.core.MemeTemplate
import com.theapache64.ghmm.util.JsonUtils
import kotlinx.serialization.decodeFromString
import java.awt.Rectangle

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

    override fun getTextCoordinates(data: DrakeData): List<Pair<String, Rectangle>> {
        return listOf(
            data.text1 to Rectangle(500, 0, 500, 500),
            data.text2 to Rectangle(500, 500, 500, 500)
        )
    }
}