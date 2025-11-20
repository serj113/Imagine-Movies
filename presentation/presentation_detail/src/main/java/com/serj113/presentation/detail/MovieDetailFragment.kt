package com.serj113.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serj113.imaginemovies.base.presentation.BaseFragment
import com.serj113.common.presentation.adapter.HorizontalDecoration
import com.serj113.common.presentation.adapter.ItemViewAdapter
import com.serj113.common.presentation.adapter.bindable.ItemView
import com.serj113.common.presentation.util.navigateTo
import com.serj113.imaginemovies.common_presentation.R
import com.serj113.imaginemovies.presentation_detail.BuildConfig
import com.serj113.imaginemovies.presentation_detail.databinding.MovieDetailFragmentBinding
import com.serj113.imaginemovies.base.model.Movie
import com.serj113.presentation.detail.itemviews.CastItemView
import com.serj113.presentation.detail.itemviews.MovieItemView
import com.serj113.presentation.detail.itemviews.ReviewItemView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailFragmentBinding>() {

    private val viewModel: MovieDetailViewModel by activityViewModels()
    private var castItemViewAdapter: ItemViewAdapter? = null
    private var reviewItemViewAdapter: ItemViewAdapter? = null
    private var recoItemViewAdapter: ItemViewAdapter? = null
    private var similarItemViewAdapter: ItemViewAdapter? = null

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = MovieDetailFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getMovieBackdrop().observe(viewLifecycleOwner) { imageUrl ->
            binding?.ivBackdrop?.let {
                Glide.with(requireContext()).load(imageUrl).into(it)
            }
        }

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

        viewModel.getListCast().observe(viewLifecycleOwner) {
            val itemViews = mutableListOf<ItemView>()
            if (it.isNotEmpty()) {
                itemViews.addAll(
                    it.map { cast ->
                        CastItemView().apply {
                            state.cast = cast
                        }
                    }
                )
            }
            val visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            binding?.tvCasts?.visibility = visibility
            binding?.rvCasts?.visibility = visibility
            castItemViewAdapter?.setItems(itemViews)
        }

        viewModel.getListReview().observe(viewLifecycleOwner) {
            val itemViews = mutableListOf<ItemView>()
            if (it.isNotEmpty()) {
                itemViews.addAll(
                    it.take(4).map { review ->
                        ReviewItemView().apply {
                            state.review = review
                        }
                    }
                )
            }
            val visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            binding?.tvReviews?.visibility = visibility
            binding?.rvReviews?.visibility = visibility
            reviewItemViewAdapter?.setItems(itemViews)
        }

        viewModel.getMovieRecommendations().observe(viewLifecycleOwner) {
            val itemViews = mutableListOf<ItemView>()
            if (it.isNotEmpty()) {
                itemViews.addAll(getMovieItemViews(it))
            }
            val visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            binding?.tvRecommendations?.visibility = visibility
            binding?.rvRecommendations?.visibility = visibility
            recoItemViewAdapter?.setItems(itemViews)
        }

        viewModel.getMovieSimilar().observe(viewLifecycleOwner) {
            val itemViews = mutableListOf<ItemView>()
            if (it.isNotEmpty()) {
                itemViews.addAll(getMovieItemViews(it))
            }
            val visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            binding?.tvSimilar?.visibility = visibility
            binding?.rvSimilar?.visibility = visibility
            similarItemViewAdapter?.setItems(itemViews)
        }

        arguments?.let { arguments ->
            viewModel.bind(MovieDetailFragmentArgs.fromBundle(arguments).movie)
        }
    }

    private fun initAdapter() {
        castItemViewAdapter = ItemViewAdapter()
        reviewItemViewAdapter = ItemViewAdapter()
        recoItemViewAdapter = ItemViewAdapter()
        similarItemViewAdapter = ItemViewAdapter()
        binding?.let {
            it.rvCasts.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL,
                false
            )
            it.rvCasts.adapter = castItemViewAdapter
            it.rvCasts.addItemDecoration(HorizontalDecoration(requireContext()))
            it.rvCasts.addOnItemTouchListener(
                object : RecyclerView.OnItemTouchListener {

                    override fun onTouchEvent(view: RecyclerView, event: MotionEvent) {}

                    override fun onInterceptTouchEvent(view: RecyclerView, event: MotionEvent): Boolean {
                        when (event.action) {
                            MotionEvent.ACTION_DOWN -> {
                                it.rvCasts.parent?.requestDisallowInterceptTouchEvent(true)
                            }
                        }
                        return false
                    }

                    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
                }
            )

            it.rvReviews.layoutManager = LinearLayoutManager(requireContext())
            it.rvReviews.adapter = reviewItemViewAdapter

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
            it.rvSimilar.addItemDecoration(HorizontalDecoration(requireContext()))
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