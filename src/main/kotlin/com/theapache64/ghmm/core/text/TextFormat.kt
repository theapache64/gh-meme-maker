package com.theapache64.ghmm.core.text

/**
 * A class which provides formatting flags for use when rendering text.
 */
object TextFormat {
    /**
     * No additional formatting flags should be used.
     */
    const val NONE = 0

    /**
     * Indicates that the renderer should not use anti-aliasing when rendering the text.
     */
    const val NO_ANTI_ALIASING = 1

    /**
     * Indicates that the first line of the text should always be visible.
     */
    const val FIRST_LINE_VISIBLE = 2

    /**
     * Gets whether the target *format* contains a specific *flag*.
     *
     * @param format    The compiled format flags
     * @param flag        The flag to find in the format flags
     * @return true if the format flags contains the specific flag; otherwise, false
     */
	@JvmStatic
	fun isEnabled(format: Int, flag: Int): Boolean {
        return format and flag == flag
    }
}