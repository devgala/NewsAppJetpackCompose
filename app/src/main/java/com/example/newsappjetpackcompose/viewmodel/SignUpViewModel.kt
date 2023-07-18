package com.example.newsappjetpackcompose.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappjetpackcompose.repository.AuthRepository
import com.example.newsappjetpackcompose.states.GoogleSignInState
import com.example.newsappjetpackcompose.states.SignInState
import com.example.newsappjetpackcompose.util.AuthResource
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private val _signUpState = Channel<SignInState>()
    val signUpState = _signUpState.receiveAsFlow()

    private val _googleState = mutableStateOf(GoogleSignInState())
    val googleState: State<GoogleSignInState> = _googleState

    fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
        repository.googleSignIn(credential).collect { result ->
            when (result) {
                is AuthResource.Success -> {
                    _googleState.value = GoogleSignInState(success = result.data)
                }

                is AuthResource.Loading -> {
                    _googleState.value = GoogleSignInState(loading = true)
                }

                is AuthResource.Error -> {
                    _googleState.value = GoogleSignInState(error = result.message!!)
                }
            }
        }
    }

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        repository.registerUser(email, password).collect { result ->
            when (result) {
                is AuthResource.Success -> {
                    _signUpState.send(SignInState(isSuccess = "Sign Up Success"))
                }

                is AuthResource.Loading -> {
                    _signUpState.send(SignInState(isLoading = true))
                }

                is AuthResource.Error -> {
                    _signUpState.send(SignInState(isError = result.message))
                }
            }
        }
    }
}