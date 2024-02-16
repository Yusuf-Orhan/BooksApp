package com.yusuforhan.booksapp.android.presentation.detail.viewmodel

import com.yusuforhan.booksapp.android.data.model.remote.Book

data class DetailState(
    val isLoading : Boolean? = null,
    val isError : String? = null,
    val isFavorite : Boolean? = null,
    val book : Book? = null,
    val userId : String? = null,
    val addToCart : Boolean? = null
)
