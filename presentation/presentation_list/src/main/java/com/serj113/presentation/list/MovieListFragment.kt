package com.serj113.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.serj113.base_presentation.BaseFragment
import com.serj113.common.presentation.adapter.ListLoadStateAdapter
import com.serj113.presentation.list.databinding.MovieListFragmentBinding
import com.serj113.common.presentation.util.navigateTo
import com.serj113.model.Movie
import com.serj113.presentation.list.MovieListFragmentDirections.actionMovieListFragmentToDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : BaseFragment<MovieListFragmentBinding>() {

    private val viewModel: MovieListViewModel by viewModels()
    private var adapter: MoviePagingAdapter? = null

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

        viewModel.listViewState.observe(viewLifecycleOwner, Observer {
            when(it) {
                is MovieListViewState.Success -> {
                    it.data?.let { pagedList->
                        lifecycleScope.launch {
                            adapter?.submitData(pagedList)
                        }
                    }
                }
                is MovieListViewState.Error -> {

                }
                else -> {

                }
            }
        })
    }

    override fun onDestroy() {
        adapter = null
        binding?.let {
            it.recyclerView.adapter = null
        }
        _binding = null
        super.onDestroy()
    }

    private fun initAdapter() {
        adapter = MoviePagingAdapter(::onClick)
        binding?.let {
            it.recyclerView.adapter = adapter?.withLoadStateFooter(
                ListLoadStateAdapter(this@MovieListFragment::onClickRetry)
            )
        }
    }

    private fun navigateToMovieDetail(movie: Movie) {
        navigateTo(actionMovieListFragmentToDetailFragment(movie))
    }

    private fun onClick(movie: Movie) {
        navigateToMovieDetail(movie)
    }

    private fun onClickRetry() {
        adapter?.retry()
    }
}