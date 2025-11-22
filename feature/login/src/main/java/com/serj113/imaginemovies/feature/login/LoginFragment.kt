package com.serj113.imaginemovies.feature.login

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.serj113.imaginemovies.common.presentation.util.navigateTo
import com.serj113.imaginemovies.feature.login.LoginFragmentDirections.actionLoginFragmentToMovieListFragment
import com.serj113.imaginemovies.feature.login.databinding.LoginFragmentBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(123123123))
//            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        viewModel.setupAuth()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { viewState ->
                when (viewState) {
                    is LoginViewState.GoToMovieList -> navigateToMovieList()
                    is LoginViewState.Error -> { }
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.signInButton?.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.signInFirebaseAuth(task)
        }
    }

    private fun navigateToMovieList() {
        navigateTo(actionLoginFragmentToMovieListFragment())
    }

    companion object {
        internal const val REQUEST_CODE_SIGN_IN = 999
    }
}