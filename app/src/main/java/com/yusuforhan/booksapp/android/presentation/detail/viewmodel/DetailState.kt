package com.yusuforhan.booksapp.android.presentation.detail.viewmodel

import com.yusuforhan.booksapp.android.data.model.remote.Books

data class DetailState(
    val isLoading : Boolean? = null,
    val isError : String? = null,
    val isFavorite : Boolean? = null,
    val book : Books? = null
)
