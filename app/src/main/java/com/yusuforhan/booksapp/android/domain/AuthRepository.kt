package com.yusuforhan.booksapp.android.domain

import com.yusuforhan.booksapp.android.data.model.remote.CrudResponse
import com.yusuforhan.booksapp.android.data.model.remote.SignInModel
import com.yusuforhan.booksapp.android.data.model.remote.SignUpModel

interface AuthRepository {
    suspend fun signIn(signInModel : SignInModel) : CrudResponse
    suspend fun signUp(signUpModel: SignUpModel) : CrudResponse
}