package com.serj113.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.serj113.presentation.databinding.FragmentCastBinding

class CastFragment : Fragment() {

    private lateinit var binding: FragmentCastBinding
    private val viewModel: MovieDetailViewModel by activityViewModels()
    private val adapter = CastRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCastBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getListCast().observe(viewLifecycleOwner, Observer {
            adapter.set(it)
        })
    }
}