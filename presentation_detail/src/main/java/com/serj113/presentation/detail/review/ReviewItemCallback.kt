package com.serj113.presentation.detail.review

import androidx.recyclerview.widget.DiffUtil
import com.serj113.model.Review

object ReviewItemCallback : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Review, newItem: Review) = oldItem == newItem
}