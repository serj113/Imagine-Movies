package com.serj113.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.serj113.base_presentation.BaseFragment
import com.serj113.common.presentation.util.navigateTo
import com.serj113.model.Movie
import com.serj113.presentation.list.MovieListFragmentDirections.actionMovieListFragmentToDetailFragment
import com.serj113.presentation.list.databinding.MovieListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : BaseFragment<MovieListFragmentBinding>() {

    private val viewModel: MovieListViewModel by viewModels()
    private var movieAdapter: MovieRecyclerViewAdapter? = null

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = MovieListFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.movieListViewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is MovieListViewState.Success -> {
                    it.data.let { list ->
                        lifecycleScope.launch {
                            movieAdapter?.addItems(list)
                        }
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
        movieAdapter = null
        binding?.let {
            it.recyclerView.adapter = null
        }
        _binding = null
        super.onDestroy()
    }

    private fun initAdapter() {
        movieAdapter = MovieRecyclerViewAdapter(::onClick)
        binding?.let {
            it.recyclerView.adapter = movieAdapter
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
