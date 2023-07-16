package com.example.newsappjetpackcompose.repository

import com.example.newsappjetpackcompose.util.AuthResource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email:String, password:String): Flow<AuthResource<AuthResult>>
    fun registerUser(email:String, password:String): Flow<AuthResource<AuthResult>>
    fun googleSignIn(credential: AuthCredential): Flow<AuthResource<AuthResult>>
}