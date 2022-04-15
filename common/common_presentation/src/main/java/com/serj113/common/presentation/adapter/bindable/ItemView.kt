package com.serj113.common.presentation.adapter.bindable

import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class ItemView: BindingCreator {

    abstract val viewType: Int

    abstract fun bindView(viewBinding: ViewBinding)

    abstract fun getView(): View?
}
