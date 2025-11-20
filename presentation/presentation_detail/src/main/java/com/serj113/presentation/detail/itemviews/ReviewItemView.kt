package com.serj113.presentation.detail.itemviews

import android.view.LayoutInflater
import android.view.ViewGroup
import com.serj113.common.presentation.adapter.bindable.BindableItemView
import com.serj113.imaginemovies.presentation_detail.databinding.ReviewItemViewBinding
import com.serj113.imaginemovies.base.model.Review

class ReviewItemView : BindableItemView<ReviewItemViewBinding, ReviewItemView.State>() {

    override val viewType: Int = ReviewItemView::javaClass.hashCode()
    override val state = State()

    override fun bind(binding: ReviewItemViewBinding) {
        binding.tvReviewAuthor.text = state.review.author
        binding.tvReviewContent.text = state.review.content
    }

    override fun createBinding(
        parent: ViewGroup
    ) = ReviewItemViewBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )

    class State {
        var review: Review = Review()
    }
}