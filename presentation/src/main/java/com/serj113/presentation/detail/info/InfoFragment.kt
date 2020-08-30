package com.serj113.presentation.detail.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.serj113.presentation.databinding.FragmentInfoBinding
import com.serj113.presentation.detail.MovieDetailViewModel

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private val viewModel: MovieDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getMovieSynopsis().observe(viewLifecycleOwner, Observer {
            binding.tvOverview.text = it
        })
    }
}