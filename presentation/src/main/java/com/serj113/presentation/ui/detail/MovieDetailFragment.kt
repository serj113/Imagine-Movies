package com.serj113.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.serj113.presentation.BuildConfig
import com.serj113.presentation.databinding.MovieDetailFragmentBinding
import com.serj113.presentation.util.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MovieDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding: MovieDetailFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        viewModel.getMovieBackdrop().observe(viewLifecycleOwner, Observer {
            Glide.with(requireContext()).load(it).into(binding.ivBackdrop)
        })

        arguments?.let { arguments ->
            viewModel.bind(MovieDetailFragmentArgs.fromBundle(arguments).movie)
        }

    }

}