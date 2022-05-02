package com.serj113.presentation.detail.itemviews

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.serj113.common.presentation.adapter.bindable.BindableItemView
import com.serj113.model.Cast
import com.serj113.presentation.detail.BuildConfig
import com.serj113.presentation.detail.databinding.CastItemViewBinding

class CastItemView : BindableItemView<CastItemViewBinding, CastItemView.State>() {

    override val viewType: Int = MovieItemView::javaClass.hashCode()
    override val state = State()

    override fun bind(binding: CastItemViewBinding) {
        if (state.cast.profilePath?.isNotEmpty() == true) {
            Glide.with(binding.ivCast)
                .load(BuildConfig.IMAGE_URL + state.cast.profilePath)
                .into(binding.ivCast)
        }
        binding.tvCastName.text = state.cast.name
    }

    override fun createBinding(
        parent: ViewGroup
    ) = CastItemViewBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )

    class State {
        var cast: Cast = Cast()
    }
}