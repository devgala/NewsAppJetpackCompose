package com.example.newsappjetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappjetpackcompose.signIn.AuthRepository
import com.example.newsappjetpackcompose.states.SignInState
import com.example.newsappjetpackcompose.util.AuthResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    fun loginUser(email:String, password:String) = viewModelScope.launch {
        repository.loginUser(email, password).collect{ result ->
            when(result){
                is AuthResource.Success -> {
                    _signInState.send(SignInState(isSuccess = "Sign In Success"))
                }
                is AuthResource.Loading -> {
                    _signInState.send(SignInState(isLoading = true))
                }
                is AuthResource.Error -> {
                    _signInState.send(SignInState(isError = result.message))
                }
            }
        }
    }

//    private val _state = MutableStateFlow(SignInState())
//    val state = _state.asStateFlow()
//
//    fun onSignInResult(result: SignInResult) {
//        _state.update { it.copy(
//            isSignInSuccessful = result.data!=null,
//            signInError = result.errorMessage
//        ) }
//    }
//
//    fun resetState() {
//        _state.update { SignInState() }
//    }
}