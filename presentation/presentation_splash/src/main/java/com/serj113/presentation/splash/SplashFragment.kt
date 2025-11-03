package com.serj113.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.serj113.base_presentation.BaseFragment
import com.serj113.common.presentation.util.navigateTo
import com.serj113.imaginemovies.presentation_splash.databinding.SplashFragmentBinding
import com.serj113.presentation.splash.SplashFragmentDirections.actionSplashFragmentToLoginFragment
import com.serj113.presentation.splash.SplashFragmentDirections.actionSplashFragmentToMovieListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding>() {

    private val viewModel: SplashViewModel by viewModels()
//    private var _binding: SplashFragmentBinding? = null
//    private val binding get() = _binding

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

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = SplashFragmentBinding.inflate(inflater, container, false)
//        return binding?.root
//    }

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
        navigateTo(actionSplashFragmentToLoginFragment())
    }

    private fun navigateToMovieList() {
        navigateTo(actionSplashFragmentToMovieListFragment())
    }
}