package com.serj113.presentation.detail

import androidx.recyclerview.widget.DiffUtil
import com.serj113.domain.entity.Cast

object CastItemCallback : DiffUtil.ItemCallback<Cast>() {
    override fun areItemsTheSame(oldItem: Cast, newItem: Cast) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Cast, newItem: Cast) = oldItem == newItem
}