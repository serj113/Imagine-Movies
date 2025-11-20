package com.serj113.imaginemovies.base.presentation.extention

import com.serj113.imaginemovies.base.presentation.util.Event

fun Event<Boolean>.isTrue(): Boolean {
    return if (hasBeenHandled) {
        false
    } else {
        val value = getContentIfNotHandled()
        (value != null && value)
    }
}