package com.serj113.common.presentation.adapter.bindable

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

interface BindingCreator {
    fun createViewBinding(parent: ViewGroup): ViewBinding
}