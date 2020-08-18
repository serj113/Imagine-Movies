package com.serj113.presentation.detail

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.serj113.domain.base.Entity
import com.serj113.presentation.R
import com.serj113.presentation.adapter.ListLoadStateAdapter
import com.serj113.presentation.databinding.FragmentReviewBinding
import kotlinx.coroutines.launch

class ReviewFragment : Fragment() {

    private lateinit var binding: FragmentReviewBinding
    private lateinit var adapter: ReviewPagingAdapter
    private val viewModel: MovieDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        adapter = ReviewPagingAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView
            .addItemDecoration(DividerItemDecoration(context, ClipDrawable.HORIZONTAL))
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            ListLoadStateAdapter(this::onClickRetry)
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getListReview().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Entity.Success -> {
                    lifecycleScope.launch {
                        adapter.submitData(it.data)
                    }
                }
                else -> { }
            }
        })
    }

    private fun onClickRetry() {
        adapter.retry()
    }
}