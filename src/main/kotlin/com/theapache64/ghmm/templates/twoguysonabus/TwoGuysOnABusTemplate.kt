package com.theapache64.ghmm.templates.twoguysonabus

import com.theapache64.ghmm.core.MemeTemplate
import com.theapache64.ghmm.util.JsonUtils
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import java.awt.Rectangle

class TwoGuysOnABusTemplate(isDebug: Boolean = false) : MemeTemplate<TwoGuysOnABusData>(isDebug) {
    override fun getId(): String = "two-guys-on-a-bus-meme"

    override fun getTemplateImageName(): String {
        return "two_guys_on_a_bus.jpg"
    }

    override fun getFontSize(data: TwoGuysOnABusData): Float {
        return data.fontSize
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun decodeJson(jsonStringData: String): TwoGuysOnABusData {
        return JsonUtils.json.decodeFromString(jsonStringData)
    }

    override fun getTextCoordinates(data: TwoGuysOnABusData): List<Pair<String, Rectangle>> {
        return listOf(
            data.sadGuy to Rectangle(103, 375, 275, 157),
            data.happyGuy to Rectangle(772, 369, 365, 109),
        )
    }
}