package com.github.edwnmrtnz.awesomeforms.library

import android.text.InputFilter
import android.util.Log


object EmojiFilter {
    val filter: Array<InputFilter>
        get() {
            val emojiFilter = InputFilter { source, start, end, spanned, dStart, dEnd ->
                for (index in start until end) {
                    val type = Character.getType(source[index])
                    if (type == Character.SURROGATE.toInt() || type == Character.NON_SPACING_MARK.toInt() || type == Character.OTHER_SYMBOL.toInt()) {
                        Log.e("EmojiFilter" , "source: $source, start: $start end: $end spanned: $spanned dStart: $dStart dEnd: $dEnd")
                        try {
                            return@InputFilter "${source.subSequence(dStart, dEnd)}"
                        } catch (exception: Exception) {
                            return@InputFilter "${spanned.subSequence(dStart, dEnd)}"
                        }
                    }
                }
                null
            }
            return arrayOf(emojiFilter)
        }
}