package com.serj113.imaginemovies.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.serj113.imaginemovies.R
import com.serj113.imaginemovies.databinding.MovieListFragmentBinding
import com.serj113.imaginemovies.domain.base.NetworkState
import com.serj113.imaginemovies.util.ViewModelFactory
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
        adapter = MovieListAdapter()

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

}
