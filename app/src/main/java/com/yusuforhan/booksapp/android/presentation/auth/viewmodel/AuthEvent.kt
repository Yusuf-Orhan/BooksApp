package com.yusuforhan.booksapp.android.presentation.auth.viewmodel

import com.yusuforhan.booksapp.android.data.model.remote.SignInModel
import com.yusuforhan.booksapp.android.data.model.remote.SignUpModel

sealed class AuthEvent {
    data class SignUp(val signUpModel: SignUpModel) : AuthEvent()
    data class SignIn(val signInModel : SignInModel) : AuthEvent()

}