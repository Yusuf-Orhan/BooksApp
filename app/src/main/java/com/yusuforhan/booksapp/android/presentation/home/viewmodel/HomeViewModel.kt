package com.yusuforhan.booksapp.android.presentation.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.domain.usecase.GetAllBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllBooksUseCase: GetAllBooksUseCase
): ViewModel() {

    private var _booksList = MutableStateFlow<List<BooksModel>>(emptyList())
    val booksList : StateFlow<List<BooksModel>> = _booksList
    init {
        _booksList = getAllBooksUseCase.booksList
        getAllBooks()
    }
    private fun getAllBooks() = viewModelScope.launch {
        getAllBooksUseCase()
    }

}