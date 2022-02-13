package com.serj113.presentation.list

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

interface BindingCreator {
    fun createViewBinding(parent: ViewGroup): ViewBinding
}