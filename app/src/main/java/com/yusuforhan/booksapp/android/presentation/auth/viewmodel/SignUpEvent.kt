package com.yusuforhan.booksapp.android.presentation.auth.viewmodel

import com.yusuforhan.booksapp.android.data.model.remote.SignUpModel

sealed class SignUpEvent {
    data class SignUp(val signUpModel: SignUpModel) : SignUpEvent()

}