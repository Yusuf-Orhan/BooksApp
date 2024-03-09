package com.yusuforhan.booksapp.android.domain.repository

import com.yusuforhan.booksapp.android.data.model.remote.CrudResponse
import com.yusuforhan.booksapp.android.data.model.remote.SignInModel
import com.yusuforhan.booksapp.android.data.model.remote.SignUpModel
import com.yusuforhan.booksapp.android.data.model.remote.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signIn(signInModel : SignInModel) : CrudResponse
    suspend fun signUp(signUpModel: SignUpModel) : CrudResponse
    suspend fun saveIsLogin(userId : String)
    suspend fun getUserById(userId : String) : UserModel
    fun getIsLogin() : Flow<String?>
    suspend fun signOut()
}