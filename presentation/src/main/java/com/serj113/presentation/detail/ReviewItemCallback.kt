package com.serj113.presentation.detail

import androidx.recyclerview.widget.DiffUtil
import com.serj113.domain.entity.Review

object ReviewItemCallback : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Review, newItem: Review) = oldItem == newItem
}