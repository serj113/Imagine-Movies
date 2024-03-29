package com.serj113.presentation.list.itemviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.serj113.common.presentation.adapter.ItemViewAdapter
import com.serj113.common.presentation.adapter.bindable.BindableItemView
import com.serj113.common.presentation.adapter.bindable.ItemView
import com.serj113.presentation.list.R
import com.serj113.presentation.list.databinding.PopularMoviesItemViewBinding

class PopularMoviesItemView :
    BindableItemView<PopularMoviesItemViewBinding, PopularMoviesItemView.State>() {

    override val viewType: Int = PopularMoviesItemView::class.java.hashCode()
    override val state: State = State()

    private val itemViewAdapter by lazy { ItemViewAdapter() }

    override fun bind(binding: PopularMoviesItemViewBinding) {
        itemViewAdapter.setItems(state.popularMovieItemViews)
    }

    override fun createBinding(parent: ViewGroup): PopularMoviesItemViewBinding {
        val binding = PopularMoviesItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        binding.rv.layoutManager = LinearLayoutManager(parent.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rv.adapter = itemViewAdapter
        binding.rv.addItemDecoration(
            DividerItemDecoration(parent.context, DividerItemDecoration.HORIZONTAL).apply {
                AppCompatResources.getDrawable(parent.context, R.drawable.horizontal_decoration)
                    ?.let { setDrawable(it) }
            }
        )
        return binding
    }

    class State {
        var popularMovieItemViews: List<ItemView> = listOf()
    }
}