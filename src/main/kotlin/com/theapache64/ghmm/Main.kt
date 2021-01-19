package com.theapache64.ghmm

fun main(args: Array<String>) {
    if (args.size != 4) {
        val templateId = args[1]
        val data = args[3]

    } else {
        error("Invalid argument size '${args.size}'")
    }
}