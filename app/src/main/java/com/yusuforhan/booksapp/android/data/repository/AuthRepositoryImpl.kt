package com.yusuforhan.booksapp.android.data.repository

import com.yusuforhan.booksapp.android.data.model.remote.SignInModel
import com.yusuforhan.booksapp.android.data.model.remote.SignUpModel
import com.yusuforhan.booksapp.android.domain.repository.AuthRepository
import com.yusuforhan.booksapp.android.domain.source.local.LocalDataSource
import com.yusuforhan.booksapp.android.domain.source.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AuthRepository {
    override suspend fun signIn(signInModel: SignInModel) = remoteDataSource.signIn(signInModel)


    override suspend fun signUp(signUpModel: SignUpModel) = remoteDataSource.signUp(signUpModel)
    override suspend fun saveIsLogin() {
        localDataSource.saveIsLogin()
    }

    override  fun getIsLogin(): Flow<Boolean> {
        return localDataSource.getIsLogin()
    }
}