package com.serj113.presentation.list.itemviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.serj113.common.presentation.adapter.ItemViewAdapter
import com.serj113.common.presentation.adapter.bindable.BindableItemView
import com.serj113.common.presentation.adapter.bindable.ItemView
import com.serj113.presentation.list.databinding.RecycleItemViewBinding

class PopularMoviesItemView :
    BindableItemView<RecycleItemViewBinding, PopularMoviesItemView.State>() {

    override val viewType: Int = PopularMoviesItemView::class.java.hashCode()
    override val state: State = State()

    private val itemViewAdapter by lazy { ItemViewAdapter() }

    private var layoutManager: LinearLayoutManager? = null

    override fun bind(binding: RecycleItemViewBinding, state: State) {
        itemViewAdapter.setItems(state.popularMovieItemViews)
    }

    override fun createBinding(parent: ViewGroup): RecycleItemViewBinding {
        val binding = RecycleItemViewBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.root.layoutManager = layoutManager
        binding.root.adapter = itemViewAdapter
        return binding
    }

    class State {
        var popularMovieItemViews: List<ItemView> = listOf()
    }
}