package com.serj113.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.serj113.base_presentation.util.Event
import com.serj113.imaginemovies.base.domain.interactor.LoginUseCase
import com.serj113.imaginemovies.base.model.Account
import com.serj113.imaginemovies.base.model.AuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase
) : ViewModel() {

    private lateinit var auth: FirebaseAuth
    private val _viewState = MutableLiveData<Event<LoginViewState>>()
    val viewState: LiveData<Event<LoginViewState>> = _viewState

    fun signInFirebaseAuth(task: Task<GoogleSignInAccount>) {
        val account = task.getResult(ApiException::class.java)
        account?.idToken?.let {
            firebaseAuthWithGoogle(it)
        }
    }

    fun setupAuth() {
        auth = Firebase.auth
    }

    private fun firebaseAuthWithGoogle(idToken: String) = viewModelScope.launch {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val task = auth.signInWithCredential(credential).await()
        task?.let { authResult ->
            authResult.user?.getIdToken(true)?.await()?.token?.let { token ->
                val account = Account(
                    authResult.user?.uid ?: "",
                    authResult.user?.displayName ?: "",
                    authResult.user?.email ?: ""
                )
                val authToken = AuthToken(token)
                useCase.invoke(LoginUseCase.Args(account, authToken)).collect {
                    _viewState.value =
                        Event(LoginViewState.GoToMovieList)
                }
            }
        }
    }
}
