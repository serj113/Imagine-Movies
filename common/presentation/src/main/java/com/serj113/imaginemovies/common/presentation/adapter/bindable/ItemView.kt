package com.serj113.imaginemovies.common.presentation.adapter.bindable

import android.view.View
import androidx.viewbinding.ViewBinding

abstract class ItemView: BindingCreator {

    abstract val viewType: Int

    abstract fun bindView(viewBinding: ViewBinding)

    abstract fun getView(): View?
}
