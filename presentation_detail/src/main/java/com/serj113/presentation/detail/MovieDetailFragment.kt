package com.serj113.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.serj113.presentation.detail.databinding.MovieDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: MovieDetailFragmentBinding
    private lateinit var viewPagerAdapter: MovieDetailViewPagerAdapter

    private val viewModel: MovieDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        viewPagerAdapter = MovieDetailViewPagerAdapter(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = viewPagerAdapter.getTitle(position)
        }.attach()

        viewModel.getMovieBackdrop().observe(viewLifecycleOwner, Observer {
            Glide.with(requireContext()).load(it).into(binding.ivBackdrop)
        })

        arguments?.let { arguments ->
            viewModel.bind(MovieDetailFragmentArgs.fromBundle(arguments).movie)
        }
    }
}