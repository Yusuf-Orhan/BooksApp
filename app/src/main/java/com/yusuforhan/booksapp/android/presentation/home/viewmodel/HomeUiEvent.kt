package com.yusuforhan.booksapp.android.presentation.home.viewmodel

sealed class HomeUiEvent {
    data class SearchBooks(val query : String) : HomeUiEvent()
    data object GetBooks : HomeUiEvent()
    data class GetBooksByCategory(val category : String) : HomeUiEvent()
}
