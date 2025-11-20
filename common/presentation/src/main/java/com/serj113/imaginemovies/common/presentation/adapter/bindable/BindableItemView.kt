package com.serj113.imaginemovies.common.presentation.adapter.bindable

import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BindableItemView<V : ViewBinding, S> : ItemView() {
    lateinit var vBinding: V
    abstract val state: S

    abstract fun bind(binding: V)

    abstract fun createBinding(parent: ViewGroup): V

    override fun createViewBinding(parent: ViewGroup): ViewBinding {
        vBinding = createBinding(parent)
        return vBinding
    }

    override fun bindView(viewBinding: ViewBinding) {
        bind(viewBinding as V)
    }

    override fun getView(): View? {
        return if (this::vBinding.isInitialized) {
            vBinding.root
        } else null
    }
}
