package com.serj113.presentation.list

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.MarginLayoutParams
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serj113.base_presentation.BaseFragment
import com.serj113.common.presentation.adapter.ItemViewAdapter
import com.serj113.common.presentation.adapter.bindable.ItemView
import com.serj113.common.presentation.itemviews.TextItemView
import com.serj113.common.presentation.util.navigateTo
import com.serj113.imaginemovies.common_presentation.R
import com.serj113.imaginemovies.presentation_list.BuildConfig
import com.serj113.imaginemovies.presentation_list.databinding.MovieListFragmentBinding
import com.serj113.imaginemovies.lib.startup.StartUpMeasurer
import com.serj113.model.Movie
import com.serj113.presentation.list.MovieListFragmentDirections.actionMovieListFragmentToDetailFragment
import com.serj113.presentation.list.itemviews.GridMovieItemView
import com.serj113.presentation.list.itemviews.PopularMovieItemView
import com.serj113.presentation.list.itemviews.PopularMoviesItemView
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
        initAdapter()
        var runnable: Runnable? = null
        runnable = Runnable {
            view.removeCallbacks(runnable)
            StartUpMeasurer.stop()
        }
        view.post(runnable)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.movieListViewState.observe(viewLifecycleOwner) {
            when (it) {
                is MovieListViewState.Success -> {
                    val itemViews = mutableListOf<ItemView>()
                    val popularMovies = it.popularMovies.map { movie ->
                        PopularMovieItemView().apply {
                            state.movieTitle = movie.title
                            state.movieRating = movie.voteAverage.toString()
                            state.movieImageUrl = "${BuildConfig.IMAGE_URL}/${movie.posterPath}"
                            state.onClick = {
                                onClick(movie)
                            }
                        }
                    }
                    if (popularMovies.isNotEmpty()) {
                        itemViews.add(
                            PopularMoviesItemView().apply {
                                state.popularMovieItemViews = popularMovies
                            }
                        )
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
                        itemViews.add(
                            TextItemView().apply {
                                state.text = "Explore"
                                state.textSize = 20f
                                state.typeface = Typeface.BOLD
                                state.layoutParams = MarginLayoutParams(
                                    LayoutParams.WRAP_CONTENT,
                                    LayoutParams.WRAP_CONTENT
                                ).apply {
                                    setMargins(
                                        requireContext().resources.getDimension(R.dimen.dp_4).toInt(),
                                        0,
                                        0,
                                        requireContext().resources.getDimension(R.dimen.dp_4).toInt()
                                    )
                                }
                            }
                        )
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
        }

        viewModel.fetchMovieList()
        viewModel.fetchPopularMovieList()
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
            it.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!it.recyclerView.canScrollVertically(300)) {
                        viewModel.fetchMovieList()
                    }
                }
            })
            it.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (itemViewAdapter?.getItemView(position) is PopularMoviesItemView ||
                                itemViewAdapter?.getItemView(position) is TextItemView) {
                            2
                        } else 1
                    }
                }
            }
            it.recyclerView.adapter = itemViewAdapter
        }
    }

    private fun navigateToMovieDetail(movie: Movie) {
        navigateTo(actionMovieListFragmentToDetailFragment(movie))
    }

    private fun onClick(movie: Movie) {
        navigateToMovieDetail(movie)
    }
}
