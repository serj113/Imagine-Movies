package com.serj113.presentation.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.serj113.domain.base.NetworkState
import com.serj113.domain.entity.Movie
import com.serj113.presentation.databinding.MovieListFragmentBinding
import com.serj113.presentation.util.navigateTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: MovieListFragmentBinding
    private lateinit var adapter: MovieListAdapter

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieListFragmentBinding.inflate(inflater, container, false)
        adapter = MovieListAdapter(this::onClick)

        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.listMovies.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun navigateToMovieDetail(movie: Movie) {
        navigateTo(MovieListFragmentDirections.actionMovieListFragmentToDetailFragment(movie))
    }

    private fun onClick(movie: Movie) {
        navigateToMovieDetail(movie)
    }

}