package com.github.edwnmrtnz.awesomeforms.library

import android.text.InputFilter


object EmojiFilter {
    val filter: Array<InputFilter>
        get() {
            val emojiFilter = InputFilter { source, start, end, _, _, _ ->
                for (index in start until end) {
                    val type = Character.getType(source[index])
                    if (type == Character.SURROGATE.toInt() || type == Character.NON_SPACING_MARK.toInt() || type == Character.OTHER_SYMBOL.toInt()) {
                        return@InputFilter ""
                    }
                }
                null
            }
            return arrayOf(emojiFilter)
        }
}