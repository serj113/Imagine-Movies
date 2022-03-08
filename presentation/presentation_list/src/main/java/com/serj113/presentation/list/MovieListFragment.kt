package com.serj113.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.serj113.base_presentation.BaseFragment
import com.serj113.common.presentation.adapter.ItemViewAdapter
import com.serj113.common.presentation.adapter.bindable.ItemView
import com.serj113.common.presentation.util.navigateTo
import com.serj113.lib.startup.StartUpMeasurer
import com.serj113.model.Movie
import com.serj113.presentation.list.MovieListFragmentDirections.actionMovieListFragmentToDetailFragment
import com.serj113.presentation.list.databinding.MovieListFragmentBinding
import com.serj113.presentation.list.itemviews.GridMovieItemView
import com.serj113.presentation.list.itemviews.PopularMovieItemView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : BaseFragment<MovieListFragmentBinding>() {

    private val viewModel: MovieListViewModel by viewModels()
    private var itemViewAdapter: ItemViewAdapter? = null

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = MovieListFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var runnable: Runnable? = null
        runnable = Runnable {
            view.removeCallbacks(runnable)
            StartUpMeasurer.stop()
        }
        view.post(runnable)
        initAdapter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.movieListViewState.observe(viewLifecycleOwner, {
            when (it) {
                is MovieListViewState.Success -> {
                    val itemViews = mutableListOf<ItemView>()
                    val popularMovies = it.popularMovies.map { movie ->
                        PopularMovieItemView().apply {
                            GridMovieItemView().apply {
                                state.movieTitle = movie.title
                                state.movieRating = movie.voteAverage.toString()
                                state.movieImageUrl = "${BuildConfig.IMAGE_URL}/${movie.posterPath}"
                                state.onClick = {
                                    onClick(movie)
                                }
                            }
                        }
                    }
                    if (popularMovies.isNotEmpty()) {
//                        itemViews.add(
//
//                        )
                    }
                    it.data.let { list ->
                        val gridMovieItemViews = list.map {
                            GridMovieItemView().apply {
                                state.movieTitle = it.title
                                state.movieRating = it.voteAverage.toString()
                                state.movieImageUrl = "${BuildConfig.IMAGE_URL}/${it.posterPath}"
                                state.onClick = {
                                    onClick(it)
                                }
                            }
                        }
                        itemViews.addAll(gridMovieItemViews)
                    }
                    lifecycleScope.launch {
                        itemViewAdapter?.setItems(itemViews)
                    }
                }
                is MovieListViewState.Error -> {

                }
                else -> {

                }
            }
        })

        viewModel.fetchMovieList()
    }

    override fun onDestroy() {
        binding?.let {
            it.recyclerView.adapter = null
        }
        itemViewAdapter = null
        _binding = null
        super.onDestroy()
    }

    private fun initAdapter() {
        itemViewAdapter = ItemViewAdapter()
        binding?.let {
            it.recyclerView.adapter = itemViewAdapter
            it.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!it.recyclerView.canScrollVertically(300)) {
                        viewModel.fetchMovieList()
                    }
                }
            })
        }
    }

    private fun navigateToMovieDetail(movie: Movie) {
        navigateTo(actionMovieListFragmentToDetailFragment(movie))
    }

    private fun onClick(movie: Movie) {
        navigateToMovieDetail(movie)
    }
}
