package com.serj113.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serj113.domain.entity.Cast
import com.serj113.presentation.databinding.CastListItemBinding

class CastRecyclerViewAdapter :
    RecyclerView.Adapter<CastRecyclerViewAdapter.CastItemViewHolder>() {

    private var items = listOf<Cast>()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CastItemViewHolder, position: Int) {
        items.getOrNull(position)?.let { cast ->
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

    fun set(items: List<Cast>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class CastItemViewHolder(
        private val binding: CastListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: Cast) {

        }
    }
}