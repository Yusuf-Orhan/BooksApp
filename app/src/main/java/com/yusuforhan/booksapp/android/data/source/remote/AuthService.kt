package com.yusuforhan.booksapp.android.data.source.remote

import com.yusuforhan.booksapp.android.data.common.Constants.SIGN_UP
import com.yusuforhan.booksapp.android.data.common.Constants.SIGN_IN
import com.yusuforhan.booksapp.android.data.model.remote.CrudResponse
import com.yusuforhan.booksapp.android.data.model.remote.SignInModel
import com.yusuforhan.booksapp.android.data.model.remote.SignUpModel
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST(SIGN_UP)
    suspend fun signUp(@Body signUpModel: SignUpModel) : CrudResponse

    @POST(SIGN_IN)
    suspend fun signIn(@Body signInModel: SignInModel) : CrudResponse
}