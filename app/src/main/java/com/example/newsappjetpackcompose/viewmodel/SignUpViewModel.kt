package com.example.newsappjetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappjetpackcompose.signIn.AuthRepository
import com.example.newsappjetpackcompose.states.SignInState
import com.example.newsappjetpackcompose.util.AuthResource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private val _signUpState = Channel<SignInState>()
    val signUpState = _signUpState.receiveAsFlow()

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUser(email, password).collect { result ->
            when (result) {
                is AuthResource.Success -> {
                    _signUpState.send(SignInState(isSuccess = "Sign In Success"))
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