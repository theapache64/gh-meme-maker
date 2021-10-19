package com.theapache64.ghmm.templates.runningawayballoon

import com.theapache64.ghmm.core.MemeTemplate
import com.theapache64.ghmm.util.JsonUtils
import kotlinx.serialization.decodeFromString
import java.awt.Rectangle

class RunningAwayBalloonTemplate(isDebug: Boolean = true) : MemeTemplate<RunningAwayBalloonData>(isDebug) {
    override fun getId(): String {
        return "running-away-balloon-meme"
    }

    override fun getTemplateImageName(): String {
        return "running_away_balloon.jpg"
    }

    override fun getFontSize(data: RunningAwayBalloonData): Float {
        return data.fontSize
    }

    override fun decodeJson(jsonStringData: String): RunningAwayBalloonData {
        return JsonUtils.json.decodeFromString(jsonStringData)
    }

    override fun getTextCoordinates(data: RunningAwayBalloonData): List<Pair<String, Rectangle>> {
        // 799,347,498,83
        // 205,1016,0,785
        return listOf(
            data.balloonText to Rectangle(502, 84, 296, 268),
            data.pinkPerson to Rectangle(2, 756, 200, 267),
            data.greyPerson to Rectangle(11, 401, 184, 116), // Cell 1
            data.greyPerson to Rectangle(246, 884, 144, 93), // Cell 2
        )
    }
}