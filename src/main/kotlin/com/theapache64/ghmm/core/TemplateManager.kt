package com.theapache64.ghmm.core

import com.theapache64.ghmm.templates.drake.DrakeMemeTemplate
import com.theapache64.ghmm.templates.expandingbrain.ExpandingBrainMemeTemplate
import com.theapache64.ghmm.templates.firemanburns.FiremanBurnsMemeTemplate
import com.theapache64.ghmm.templates.mask.MaskMemeTemplate
import com.theapache64.ghmm.templates.runningawayballoon.RunningAwayBalloonTemplate

object TemplateManager {
    private val memeTemplates = listOf<MemeTemplate<*>>(
        DrakeMemeTemplate(),
        MaskMemeTemplate(),
        FiremanBurnsMemeTemplate(),
        ExpandingBrainMemeTemplate(),
        RunningAwayBalloonTemplate()
        // add as many templates as you want
    )

    fun getTemplate(id: String): MemeTemplate<*>? {
        return memeTemplates.find { it.getId() == id }
    }
}