package com.theapache64.ghmm.templates.mask

import com.theapache64.ghmm.core.MemeTemplate
import com.theapache64.ghmm.util.JsonUtils
import kotlinx.serialization.decodeFromString
import java.awt.Graphics2D
import java.awt.Rectangle

class MaskMemeTemplate(isDebug: Boolean = false) : MemeTemplate<MaskData>(isDebug) {
    override fun getId(): String {
        return "mask"
    }

    override fun getTemplateImageName(): String {
        return "mask.jpg"
    }

    override fun getFontSize(data: MaskData): Float {
        return data.fontSize
    }

    override fun decodeJson(jsonStringData: String): MaskData {
        return JsonUtils.json.decodeFromString(jsonStringData)
    }

    override fun getTextCoordinates(model: MaskData): List<Pair<String, Rectangle>> {
        return mutableListOf(
            model.maskText to Rectangle(684, 73, 158, 123),
            model.revealedText to Rectangle(100, 618, 209, 150),
            model.maskText to Rectangle(654, 549, 218, 266)
        )
    }
}