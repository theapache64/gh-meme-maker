package com.theapache64.ghmm.core

import com.theapache64.expekt.should
import org.junit.Test
import java.io.FileNotFoundException

class GitHubManagerTest {
    @Test
    fun `Get issue body`() {
        GitHubManager.getBody(763282001, null)
            ?.length.should.above(10)
    }

    @Test
    fun `Get Comment body`() {
        GitHubManager.getBody(1, 763282001)
            ?.length.should.equal(6)
    }
}