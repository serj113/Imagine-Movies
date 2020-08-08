package com.serj113.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.serj113.domain.entity.Movie
import com.serj113.presentation.databinding.MovieListFragmentBinding
import com.serj113.presentation.util.navigateTo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: MovieListFragmentBinding
    private lateinit var adapter: MoviePagingAdapter

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieListFragmentBinding.inflate(inflater, container, false)
        initAdapter()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.listViewState.observe(viewLifecycleOwner, Observer {
            when(it) {
                is MovieListViewState.Success -> {
                    it.data?.let { pagedList->
                        lifecycleScope.launch {
                            adapter.submitData(pagedList)
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

    private fun initAdapter() {
        adapter = MoviePagingAdapter(::onClick)
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            MovieListLoadStateAdapter(this@MovieListFragment::onClickRetry)
        )
    }

    private fun navigateToMovieDetail(movie: Movie) {
        navigateTo(MovieListFragmentDirections.actionMovieListFragmentToDetailFragment(movie))
    }

    private fun onClick(movie: Movie) {
        navigateToMovieDetail(movie)
    }

    private fun onClickRetry() {
        adapter.retry()
    }
}