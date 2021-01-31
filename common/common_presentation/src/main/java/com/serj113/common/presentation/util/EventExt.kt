package com.serj113.common.presentation.util

fun com.serj113.common.presentation.util.Event<Boolean>.isTrue(): Boolean {
    return if (hasBeenHandled) {
        false
    } else {
        val value = getContentIfNotHandled()
        (value != null && value)
    }
}