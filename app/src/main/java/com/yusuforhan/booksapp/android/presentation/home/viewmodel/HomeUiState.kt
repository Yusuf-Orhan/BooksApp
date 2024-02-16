package com.yusuforhan.booksapp.android.presentation.home.viewmodel

import com.yusuforhan.booksapp.android.data.model.remote.Book

data class HomeUiState(
    val loading : Boolean = false,
    val books : List<Book> = emptyList(),
    val error : String = ""
)