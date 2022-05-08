package com.serj113.common.presentation.adapter

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import com.serj113.common.presentation.R

class HorizontalDecoration(context: Context) : DividerItemDecoration(context, HORIZONTAL) {
    init {
        AppCompatResources.getDrawable(context, R.drawable.horizontal_decoration)
            ?.let { drawable -> setDrawable(drawable) }
    }
}