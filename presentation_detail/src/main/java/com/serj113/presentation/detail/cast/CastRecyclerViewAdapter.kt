package com.serj113.presentation.detail.cast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serj113.model.Cast
import com.serj113.presentation.detail.BuildConfig
import com.serj113.presentation.detail.databinding.CastListItemBinding

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
            if (cast.profilePath?.isNotEmpty() == true) {
                Glide.with(binding.ivCast)
                    .load(BuildConfig.IMAGE_URL + cast.profilePath)
                    .into(binding.ivCast)
            }
            binding.tvCastName.text = cast.name
            binding.tvCastCharacter.text = cast.character
        }
    }
}