package com.serj113.presentation.util

fun Event<Boolean>.isTrue(): Boolean {
    return if (hasBeenHandled) {
        false
    } else {
        val value = getContentIfNotHandled()
        (value != null && value)
    }
}