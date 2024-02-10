package com.yusuforhan.booksapp.android.presentation.profile.viewmodel

import com.yusuforhan.booksapp.android.data.model.remote.UserModel

data class ProfileState(
    val isLoading : Boolean = false,
    val error : String = "",
    val userModel : UserModel? = null
)
