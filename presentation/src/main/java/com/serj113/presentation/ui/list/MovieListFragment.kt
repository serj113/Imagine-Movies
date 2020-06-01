package com.serj113.presentation.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.serj113.domain.base.NetworkState
import com.serj113.domain.entity.Movie
import com.serj113.presentation.databinding.MovieListFragmentBinding
import com.serj113.presentation.util.ViewModelFactory
import com.serj113.presentation.util.navigateTo
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MovieListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MovieListViewModel
    private lateinit var binding: MovieListFragmentBinding
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)
    }

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
        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        viewModel.pagedEntityMovie.observe(viewLifecycleOwner, Observer {
            if (it.state == NetworkState.SUCCESS) {
                adapter.submitList(it.value)
            }
        })
    }

    private fun navigateToMovieDetail(movie: Movie) {
        navigateTo(MovieListFragmentDirections.actionMovieListFragmentToDetailFragment(movie))
    }

    private fun onClick(movie: Movie) {
        navigateToMovieDetail(movie)
    }

}