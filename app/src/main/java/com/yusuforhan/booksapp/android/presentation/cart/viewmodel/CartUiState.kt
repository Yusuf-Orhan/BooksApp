package com.yusuforhan.booksapp.android.presentation.cart.viewmodel

import com.yusuforhan.booksapp.android.data.model.remote.Book

data class CartUiState(
    val loading : Boolean = false,
    val books : List<Book> = emptyList(),
    val error : String = "",
    val userId : String? = null,
    val isDeleted : Boolean? = null
)