package com.yusuforhan.booksapp.android.presentation.home.viewmodel

import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.domain.usecase.books.GetAllBooksUseCase
import com.yusuforhan.booksapp.android.domain.usecase.books.GetSaleBookListUseCase
import com.yusuforhan.booksapp.android.domain.usecase.books.SearchBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllBooksUseCase: GetAllBooksUseCase,
    private val searchBooksUseCase: SearchBooksUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state

    init {
        getAllBooks()
    }


    fun handleEvent(event : HomeUiEvent) {
        when(event) {
            is HomeUiEvent.SearchBooks ->  {
                searchBooks(q = event.query)
            }
        }
    }

    private fun getAllBooks() {
        getAllBooksUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = _state.value.copy(loading = true)
                is Resource.Success -> _state.value =
                    _state.value.copy(loading = false, books = result.data)

                is Resource.Error -> _state.value =
                    _state.value.copy(loading = false, error = result.message)
            }
        }.launchIn(viewModelScope)
    }
    private fun searchBooks(q : String) {
        println("Handle Event")
        searchBooksUseCase(q).onEach {result ->
            when (result) {
                is Resource.Loading -> _state.value = _state.value.copy(loading = true)
                is Resource.Success -> _state.value =
                    _state.value.copy(loading = false, books = result.data)

                is Resource.Error -> _state.value =
                    _state.value.copy(loading = false, error = result.message)
            }
        }.launchIn(viewModelScope)
    }
}