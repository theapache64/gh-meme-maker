package com.theapache64.ghmm.templates.fmburns

import com.theapache64.ghmm.core.MemeTemplate
import com.theapache64.ghmm.util.JsonUtils
import kotlinx.serialization.decodeFromString
import java.awt.Rectangle

class FiremanBurnsMemeTemplate(isDebug: Boolean = false) : MemeTemplate<FiremanBurnsData>(isDebug) {
    override fun getId(): String {
        return "fireman-burns-meme"
    }

    override fun getTemplateImageName(): String {
        return "fireman_burns.jpg"
    }

    override fun getFontSize(data: FiremanBurnsData): Float {
        return data.fontSize
    }

    override fun decodeJson(jsonStringData: String): FiremanBurnsData {
        return JsonUtils.json.decodeFromString(jsonStringData)
    }

    override fun getTextCoordinates(data: FiremanBurnsData): List<Pair<String, Rectangle>> {
        return listOf(
            data.ladySays to Rectangle(352, 16, 260, 97)
        )
    }
}