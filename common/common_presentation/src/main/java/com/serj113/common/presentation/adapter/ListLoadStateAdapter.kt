package com.serj113.common.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.serj113.imaginemovies.common_presentation.databinding.LoadStateFooterItemBinding

class ListLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ListLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {

        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder {
        return LoadStateViewHolder(
            LoadStateFooterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            retry
        )
    }

    class LoadStateViewHolder(
        private val binding: LoadStateFooterItemBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMsg.text = loadState.error.localizedMessage
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState !is LoadState.Loading
            binding.errorMsg.isVisible = loadState !is LoadState.Loading
        }
    }
}