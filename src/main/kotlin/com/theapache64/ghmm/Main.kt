package com.theapache64.ghmm

import com.theapache64.ghmm.core.TemplateManager
import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {

    if (args.size == 4) {

        // Reading templateId and data
        val templateId = args[1].toInt()
        val data = args[3]

        // Asking manager to provide correct template
        val template = TemplateManager.getTemplate(templateId)

        if (template != null) {

            // Asking template to generate meme
            println("Template: ${template::class.java.simpleName}")
            val imageFile = template.generate(data)
        } else {
            error("Invalid template id $templateId")
        }
    } else {
        error("Invalid argument size '${args.size}'")
    }
}

