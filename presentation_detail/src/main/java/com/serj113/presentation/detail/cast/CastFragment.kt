package com.serj113.presentation.detail.cast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.serj113.presentation.detail.MovieDetailViewModel
import com.serj113.presentation.detail.databinding.FragmentCastBinding

class CastFragment : Fragment() {

    private var _binding: FragmentCastBinding? = null
    private val adapter = CastRecyclerViewAdapter()
    private val viewModel: MovieDetailViewModel by activityViewModels()
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCastBinding.inflate(inflater, container, false)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.adapter = adapter
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getListCast().observe(viewLifecycleOwner, Observer {
            adapter.set(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}