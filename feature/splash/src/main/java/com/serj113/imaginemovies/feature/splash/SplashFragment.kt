package com.serj113.imaginemovies.feature.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.serj113.imaginemovies.base.presentation.BaseFragment
import com.serj113.imaginemovies.common.presentation.util.navigateTo
import com.serj113.imaginemovies.feature.splash.SplashFragmentDirections
import com.serj113.imaginemovies.feature.splash.databinding.SplashFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding>() {

    private val viewModel: SplashViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SplashFragmentBinding {
        return SplashFragmentBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadInitialData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { viewState ->
                when (viewState) {
                    is SplashViewState.GoToLogin -> navigateToLogin()
                    is SplashViewState.GoToMovieList -> navigateToMovieList()
                }
            }
        })
    }

    private fun navigateToLogin() {
        navigateTo(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
    }

    private fun navigateToMovieList() {
        navigateTo(SplashFragmentDirections.actionSplashFragmentToMovieListFragment())
    }
}