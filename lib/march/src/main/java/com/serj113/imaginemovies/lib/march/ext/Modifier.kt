package com.serj113.imaginemovies.lib.march.ext

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import com.serj113.imaginemovies.lib.march.base.Spacing.x16
import com.serj113.imaginemovies.lib.march.base.Spacing.x24
import com.serj113.imaginemovies.lib.march.base.Spacing.x32

fun Modifier.size16() = this.then(
    size(x16)
)

fun Modifier.size24() = this.then(
    size(x24)
)

fun Modifier.size32() = this.then(
    size(x32)
)
