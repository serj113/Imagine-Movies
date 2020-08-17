package com.serj113.presentation.detail

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.serj113.presentation.databinding.FragmentCastBinding

class CastFragment : Fragment() {

    private lateinit var binding: FragmentCastBinding
    private val adapter = CastRecyclerViewAdapter()
    private val viewModel: MovieDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCastBinding.inflate(inflater, container, false)
//        adapter = CastRecyclerViewAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView
            .addItemDecoration(DividerItemDecoration(context, ClipDrawable.HORIZONTAL))
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