package com.theapache64.ghmm.core

import com.theapache64.ghmm.templates.drake.DrakeMemeTemplate

object TemplateManager {
    private val memeTemplates = listOf<MemeTemplate>(
        DrakeMemeTemplate(),
        // add as many templates as you want
    )

    fun getTemplate(id: Int): MemeTemplate? {
        return memeTemplates.find { it.getId() == id }
    }
}