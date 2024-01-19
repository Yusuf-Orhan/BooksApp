package com.yusuforhan.booksapp.android.data.repository

import com.yusuforhan.booksapp.android.data.model.remote.SignInModel
import com.yusuforhan.booksapp.android.data.model.remote.SignUpModel
import com.yusuforhan.booksapp.android.domain.repository.AuthRepository
import com.yusuforhan.booksapp.android.domain.source.remote.RemoteDataSource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : AuthRepository {
    override suspend fun signIn(signInModel: SignInModel) = dataSource.signIn(signInModel)


    override suspend fun signUp(signUpModel: SignUpModel) = dataSource.signUp(signUpModel)
    override suspend fun saveIsLogin(isLogin: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun getIsLogin(): Boolean? {
        TODO("Not yet implemented")
    }
}