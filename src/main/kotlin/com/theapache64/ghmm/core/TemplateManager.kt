package com.theapache64.ghmm.core

import com.theapache64.ghmm.templates.drake.DrakeMemeTemplate
import com.theapache64.ghmm.templates.fmburns.FiremanBurnsMemeTemplate
import com.theapache64.ghmm.templates.mask.MaskMemeTemplate

object TemplateManager {
    private val memeTemplates = listOf<MemeTemplate<*>>(
        DrakeMemeTemplate(),
        MaskMemeTemplate(),
        FiremanBurnsMemeTemplate()
        // add as many templates as you want
    )

    fun getTemplate(id: String): MemeTemplate<*>? {
        return memeTemplates.find { it.getId() == id }
    }
}