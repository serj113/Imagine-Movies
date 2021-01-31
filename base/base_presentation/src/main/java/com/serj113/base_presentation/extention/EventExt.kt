package com.serj113.base_presentation.extention

import com.serj113.base_presentation.util.Event

fun Event<Boolean>.isTrue(): Boolean {
    return if (hasBeenHandled) {
        false
    } else {
        val value = getContentIfNotHandled()
        (value != null && value)
    }
}