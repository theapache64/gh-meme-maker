package com.theapache64.ghmm.templates.mask

import com.theapache64.ghmm.core.MemeTemplate
import com.theapache64.ghmm.util.JsonUtils
import kotlinx.serialization.decodeFromString
import java.awt.Rectangle

class MaskMemeTemplate(isDebug: Boolean = false) : MemeTemplate<MaskData>(isDebug) {
    override fun getId(): String {
        return "mask-meme"
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

    override fun getTextCoordinates(data: MaskData): List<Pair<String, Rectangle>> {
        return listOf(
            data.maskText to Rectangle(684, 73, 158, 123),
            data.revealedText to Rectangle(100, 618, 209, 150),
            data.maskText to Rectangle(654, 549, 218, 266)
        )
    }
}