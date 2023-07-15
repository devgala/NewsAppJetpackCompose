package com.example.newsappjetpackcompose.states

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)
