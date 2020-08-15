package com.serj113.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.serj113.domain.entity.Cast
import com.serj113.presentation.databinding.CastListItemBinding

class CastPagingAdapter :
    PagingDataAdapter<Cast, CastPagingAdapter.CastItemViewHolder>(CastItemCallback) {

    override fun onBindViewHolder(holder: CastItemViewHolder, position: Int) {
        getItem(position)?.let { cast ->
            holder.bind(cast)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastItemViewHolder {
        return CastItemViewHolder(
            CastListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class CastItemViewHolder(
        private val binding: CastListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: Cast) {

        }
    }
}