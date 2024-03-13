package com.yusuforhan.booksapp.android.presentation.home.viewmodel

import com.yusuforhan.booksapp.android.data.model.remote.Book
import com.yusuforhan.booksapp.android.data.model.remote.Category

data class HomeUiState(
    val loading : Boolean = false,
    val categoryList : List<Category> = emptyList(),
    val saleBooks : List<Book> = emptyList(),
    val books : List<Book> = emptyList(),
    val error : String = ""
)