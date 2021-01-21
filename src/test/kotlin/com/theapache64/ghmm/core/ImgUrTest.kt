package com.theapache64.ghmm.core

import com.theapache64.expekt.should
import org.junit.Test
import java.io.File


class ImgUrTest {
    @Test
    fun `Image upload`() {
        val inputFile = File("/home/theapache64/Documents/projects/gh-meme-maker/template_images/fireman_burns.jpg")
        val newImageUrl = ImgUr.uploadImage(inputFile)
        newImageUrl.should.startWith("https://i.imgur.com/")
    }
}