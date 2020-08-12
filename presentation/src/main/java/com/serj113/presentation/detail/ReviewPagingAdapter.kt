package com.serj113.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.serj113.domain.entity.Review
import com.serj113.presentation.databinding.ReviewListItemBinding

class ReviewPagingAdapter :
    PagingDataAdapter<Review, ReviewPagingAdapter.ReviewItemViewHolder>(ReviewItemCallback) {

    override fun onBindViewHolder(holder: ReviewItemViewHolder, position: Int) {
        getItem(position)?.let { review ->
            holder.bind(review)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewItemViewHolder {
        return ReviewItemViewHolder(
            ReviewListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ReviewItemViewHolder(
        private val binding: ReviewListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review) {

        }
    }
}