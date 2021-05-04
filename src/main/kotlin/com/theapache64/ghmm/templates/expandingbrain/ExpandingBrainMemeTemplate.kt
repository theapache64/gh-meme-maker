package com.theapache64.ghmm.templates.expandingbrain

import com.theapache64.ghmm.core.MemeTemplate
import com.theapache64.ghmm.util.JsonUtils
import kotlinx.serialization.decodeFromString
import java.awt.Rectangle

class ExpandingBrainMemeTemplate(isDebug: Boolean = false) : MemeTemplate<ExpandingBrainData>(isDebug) {

    override fun getId(): String {
        return "expanding-brain-meme"
    }

    override fun getTemplateImageName(): String {
        return "expanding_brain.jpg"
    }

    override fun getFontSize(data: ExpandingBrainData): Float {
        return data.fontSize
    }

    override fun decodeJson(jsonStringData: String): ExpandingBrainData {
        return JsonUtils.json.decodeFromString(jsonStringData)
    }

    override fun getTextCoordinates(data: ExpandingBrainData): List<Pair<String, Rectangle>> {
        return listOf(
            data.level1 to Rectangle(3, 3, 324, 228),
            data.level2 to Rectangle(3, 246, 329, 230),
            data.level3 to Rectangle(3, 487, 323, 208),
            data.level4 to Rectangle(3, 715, 326, 235),
        )
    }
}