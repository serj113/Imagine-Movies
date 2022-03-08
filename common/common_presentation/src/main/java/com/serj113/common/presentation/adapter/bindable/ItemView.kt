package com.serj113.common.presentation.adapter.bindable

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class ItemView: BindingCreator {
    abstract val viewType: Int
    abstract fun bind(viewBinding: ViewBinding)
    abstract fun initBinding(parent: ViewGroup)
}
