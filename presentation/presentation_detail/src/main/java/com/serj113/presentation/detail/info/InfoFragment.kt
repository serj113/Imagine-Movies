package com.serj113.presentation.detail.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.serj113.presentation.detail.MovieDetailViewModel
import com.serj113.presentation.detail.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var binding: FragmentInfoBinding? = null
    private val viewModel: MovieDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getMovieSynopsis().observe(viewLifecycleOwner, Observer {
            binding?.tvOverview?.text = it
        })

        viewModel.getMovieRating().observe(viewLifecycleOwner, Observer {
            binding?.tvVoteAverage?.text = it
        })

        viewModel.getMovieVoteCount().observe(viewLifecycleOwner, Observer {
            binding?.tvVoteCount?.text = it
        })

        viewModel.getMovieOriginalTitle().observe(viewLifecycleOwner, Observer {
            binding?.tvOriginalTitle?.text = it
        })

        viewModel.getMovieReleaseDate().observe(viewLifecycleOwner, Observer {
            binding?.tvReleaseDate?.text = it
        })

        viewModel.getMovieStatus().observe(viewLifecycleOwner, Observer {
            binding?.tvStatus?.text = it
        })

        viewModel.getMovieBudget().observe(viewLifecycleOwner, Observer {
            binding?.tvBudget?.text = it
        })

        viewModel.getMovieRevenue().observe(viewLifecycleOwner, Observer {
            binding?.tvRevenue?.text = it
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}