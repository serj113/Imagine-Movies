package com.serj113.common.presentation.adapter.bindable

import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BindableItemView<V : ViewBinding, S> : ItemView() {
    lateinit var vBinding: V
    abstract val state: S

    abstract fun bind(binding: V, state: S)

    abstract fun createBinding(parent: ViewGroup): V

    override fun initBinding(parent: ViewGroup) {
        vBinding = createBinding(parent)
    }

    override fun createViewBinding(parent: ViewGroup): ViewBinding {
        vBinding = createBinding(parent)
        return vBinding
    }

    override fun bind(viewBinding: ViewBinding) {
        bind(viewBinding as V, state)
    }

    override fun getView(): View? {
        return if (this::vBinding.isInitialized) {
            vBinding.root
        } else null
    }
}
