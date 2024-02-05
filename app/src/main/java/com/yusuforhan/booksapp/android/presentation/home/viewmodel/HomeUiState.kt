package com.yusuforhan.booksapp.android.presentation.home.viewmodel

import com.yusuforhan.booksapp.android.data.model.remote.Books

data class HomeUiState(
    val loading : Boolean = false,
    val books : List<Books> = emptyList(),
    val error : String = ""
)