package com.serj113.common.presentation.itemviews

import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginBottom
import com.serj113.common.presentation.adapter.bindable.BindableItemView
import com.serj113.common.presentation.databinding.TextViewItemBinding

class TextItemView : BindableItemView<TextViewItemBinding, TextItemView.State>() {

    override val viewType = TextItemView::class.java.hashCode()

    override val state = State()

    override fun bind(binding: TextViewItemBinding) {
        binding.root.text = state.text
    }

    override fun createBinding(parent: ViewGroup): TextViewItemBinding {
        val viewBinding = TextViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        viewBinding.root.setTextSize(TypedValue.COMPLEX_UNIT_SP, state.textSize)
        viewBinding.root.setTypeface(viewBinding.root.typeface, state.typeface)
        state.layoutParams?.let {
            viewBinding.root.layoutParams = it
        }
        return viewBinding
    }

    class State {
        var text = ""
        var textSize = 12f
        var typeface: Int = Typeface.NORMAL
        var layoutParams: ViewGroup.LayoutParams? = null
    }
}