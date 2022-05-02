package com.serj113.presentation.detail.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serj113.base_presentation.BaseFragment
import com.serj113.common.presentation.adapter.ItemViewAdapter
import com.serj113.common.presentation.adapter.bindable.ItemView
import com.serj113.common.presentation.util.navigateTo
import com.serj113.model.Movie
import com.serj113.presentation.detail.BuildConfig
import com.serj113.presentation.detail.MovieDetailFragmentDirections
import com.serj113.presentation.detail.MovieDetailViewModel
import com.serj113.presentation.detail.R
import com.serj113.presentation.detail.databinding.FragmentInfoBinding
import com.serj113.presentation.detail.itemviews.MovieItemView
import com.serj113.presentation.list.MovieListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : BaseFragment<FragmentInfoBinding>() {

    private val viewModel: MovieDetailViewModel by activityViewModels()
    private var recoItemViewAdapter: ItemViewAdapter? = null
    private var similarItemViewAdapter: ItemViewAdapter? = null

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentInfoBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getMovieSynopsis().observe(viewLifecycleOwner) {
            binding?.tvOverview?.text = it
        }

        viewModel.getMovieRating().observe(viewLifecycleOwner) {
            binding?.tvVoteAverage?.text = it
        }

        viewModel.getMovieVoteCount().observe(viewLifecycleOwner) {
            binding?.tvVoteCount?.text = it
        }

        viewModel.getMovieOriginalTitle().observe(viewLifecycleOwner) {
            binding?.tvOriginalTitle?.text = it
        }

        viewModel.getMovieReleaseDate().observe(viewLifecycleOwner) {
            binding?.tvReleaseDate?.text = it
        }

        viewModel.getMovieStatus().observe(viewLifecycleOwner) {
            binding?.tvStatus?.text = it
        }

        viewModel.getMovieBudget().observe(viewLifecycleOwner) {
            binding?.tvBudget?.text = it
        }

        viewModel.getMovieRevenue().observe(viewLifecycleOwner) {
            binding?.tvRevenue?.text = it
        }

        viewModel.getMovieRecommendations().observe(viewLifecycleOwner) {
            val itemViews = mutableListOf<ItemView>()
            if (it.isNotEmpty()) {
                itemViews.addAll(getMovieItemViews(it))
            }
            binding?.rvRecommendations?.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            recoItemViewAdapter?.setItems(itemViews)
        }

        viewModel.getMovieSimilar().observe(viewLifecycleOwner) {
            val itemViews = mutableListOf<ItemView>()
            if (it.isNotEmpty()) {
                itemViews.addAll(getMovieItemViews(it))
            }
            binding?.rvSimilar?.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            similarItemViewAdapter?.setItems(itemViews)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        recoItemViewAdapter = null
        similarItemViewAdapter = null
        _binding = null
    }

    private fun initAdapter() {
        recoItemViewAdapter = ItemViewAdapter()
        similarItemViewAdapter = ItemViewAdapter()
        binding?.let {
            it.rvRecommendations.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL,
                false
            )
            it.rvRecommendations.adapter = recoItemViewAdapter
            it.rvRecommendations.addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL).apply {
                    AppCompatResources.getDrawable(requireContext(), R.drawable.horizontal_decoration)
                        ?.let { drawable -> setDrawable(drawable) }
                }
            )
            it.rvRecommendations.addOnItemTouchListener(
                object : RecyclerView.OnItemTouchListener {

                    override fun onTouchEvent(view: RecyclerView, event: MotionEvent) {}

                    override fun onInterceptTouchEvent(view: RecyclerView, event: MotionEvent): Boolean {
                        when (event.action) {
                            MotionEvent.ACTION_DOWN -> {
                                it.rvRecommendations.parent?.requestDisallowInterceptTouchEvent(true)
                            }
                        }
                        return false
                    }

                    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
                }
            )

            it.rvSimilar.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL,
                false
            )
            it.rvSimilar.adapter = similarItemViewAdapter
            it.rvSimilar.addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL).apply {
                    AppCompatResources.getDrawable(requireContext(), R.drawable.horizontal_decoration)
                        ?.let { drawable -> setDrawable(drawable) }
                }
            )
            it.rvSimilar.addOnItemTouchListener(
                object : RecyclerView.OnItemTouchListener {

                    override fun onTouchEvent(view: RecyclerView, event: MotionEvent) {}

                    override fun onInterceptTouchEvent(view: RecyclerView, event: MotionEvent): Boolean {
                        when (event.action) {
                            MotionEvent.ACTION_DOWN -> {
                                it.rvSimilar.parent?.requestDisallowInterceptTouchEvent(true)
                            }
                        }
                        return false
                    }

                    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
                }
            )
        }
    }

    private fun getMovieItemViews(movies: List<Movie>): List<MovieItemView> {
        return movies.map { movie ->
            MovieItemView().apply {
                state.movieTitle = movie.title
                state.movieRating = movie.voteAverage.toString()
                state.movieImageUrl = "${BuildConfig.IMAGE_URL}/${movie.posterPath}"
                state.onClick = {
                    onClick(movie)
                }
            }
        }
    }

    private fun onClick(movie: Movie) {
        navigateTo(
            MovieDetailFragmentDirections
                .actionMovieDetailFragmentToMovieDetailFragment(movie)
        )
    }
}