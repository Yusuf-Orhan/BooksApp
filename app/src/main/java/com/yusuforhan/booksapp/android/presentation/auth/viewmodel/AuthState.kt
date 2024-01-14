package com.yusuforhan.booksapp.android.presentation.auth.viewmodel

data class AuthState(
    val isSuccess : Boolean? = null,
    val emptyParameter : Boolean? = null,
    val message : String? = null,
    val userId : String? = null
)