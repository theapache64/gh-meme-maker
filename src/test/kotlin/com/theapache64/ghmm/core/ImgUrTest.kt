package com.theapache64.ghmm.core

import com.theapache64.expekt.should
import org.junit.Test
import java.io.File


class ImgUrTest {
    @Test
    fun `Image upload`() {
        val inputFile = File("template_images/fireman_burns.jpg").also {
            println("Exist -> ${it.exists()}")
        }
        val newImageUrl = ImgUr.uploadImage(inputFile)
        newImageUrl.should.startWith("https://i.imgur.com/")
    }
}