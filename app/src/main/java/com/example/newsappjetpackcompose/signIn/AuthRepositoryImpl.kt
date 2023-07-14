package com.example.newsappjetpackcompose.signIn


import com.example.newsappjetpackcompose.util.AuthResource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {

    override fun loginUser(email: String, password: String): Flow<AuthResource<AuthResult>> {
        return flow {
            emit(AuthResource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(AuthResource.Success(result))
        }.catch {
            emit(AuthResource.Error(it.message.toString()))
        }
    }

    override fun registerUser(email: String, password: String): Flow<AuthResource<AuthResult>> {
        return flow {
            emit(AuthResource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(AuthResource.Success(result))
        }.catch {
            emit(AuthResource.Error(it.message.toString()))
        }
    }

    override fun googleSignIn(credential: AuthCredential): Flow<AuthResource<AuthResult>> {
        return flow {
            emit(AuthResource.Loading())
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(AuthResource.Success(result))
        }.catch {
            emit(AuthResource.Error(it.message.toString()))
        }
    }
}