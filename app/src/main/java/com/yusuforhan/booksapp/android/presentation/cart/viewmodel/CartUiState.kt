package com.yusuforhan.booksapp.android.presentation.cart.viewmodel

import com.yusuforhan.booksapp.android.data.model.remote.Books

data class CartUiState(
    val loading : Boolean = false,
    val books : List<Books> = emptyList(),
    val error : String = "",
    val userId : String? = null,
    val isDeleted : Boolean? = null
)