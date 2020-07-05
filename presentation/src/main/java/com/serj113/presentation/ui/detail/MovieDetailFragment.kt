package com.serj113.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.serj113.presentation.databinding.MovieDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: MovieDetailFragmentBinding

    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getMovieBackdrop().observe(viewLifecycleOwner, Observer {
            Glide.with(requireContext()).load(it).into(binding.ivBackdrop)
        })

        arguments?.let { arguments ->
            viewModel.bind(MovieDetailFragmentArgs.fromBundle(arguments).movie)
        }

    }

}