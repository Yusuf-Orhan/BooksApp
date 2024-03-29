package com.yusuforhan.booksapp.android.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.domain.usecase.books.GetAllBooksUseCase
import com.yusuforhan.booksapp.android.domain.usecase.books.GetBooksByCategory
import com.yusuforhan.booksapp.android.domain.usecase.books.GetCategoriesUseCase
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
    private val searchBooksUseCase: SearchBooksUseCase,
    private val getBooksByCategoryUseCase: GetBooksByCategory,
    private val getSaleBookListUseCase: GetSaleBookListUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state

    init {
        getAllBooks()
        getCategories()
    }


    fun handleEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.SearchBooks -> {
                searchBooks(q = event.query)
            }

            is HomeUiEvent.GetBooks -> {
                getAllBooks()
            }

            is HomeUiEvent.GetBooksByCategory -> {
                getBooksByCategory(event.category)
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

    private fun searchBooks(q: String) {
        searchBooksUseCase(q).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = _state.value.copy(loading = true)
                is Resource.Success -> _state.value =
                    _state.value.copy(loading = false, books = result.data)

                is Resource.Error -> _state.value =
                    _state.value.copy(loading = false, error = result.message)
            }
        }.launchIn(viewModelScope)
    }

    private fun getCategories() {
        getCategoriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(loading = false,categoryList = result.data)
                }

                is Resource.Loading -> {
                    _state.value = state.value.copy(loading = true)
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun getBooksByCategory(category: String) {
        getBooksByCategoryUseCase(category).onEach { result ->
            when(result) {
                is Resource.Success -> _state.value = state.value.copy(loading = false,books = result.data)
                is Resource.Error -> _state.value = state.value.copy(loading = false,error = result.message)
                is Resource.Loading -> _state.value = state.value.copy(loading = true)
            }
        }.launchIn(viewModelScope)
    }
    private fun getSaleBooks() {
        getSaleBookListUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> _state.value = state.value.copy(loading = false, saleBooks = result.data)
                is Resource.Error -> _state.value = state.value.copy(loading = false, error = result.message)
                is Resource.Loading -> _state.value = state.value.copy(loading = true)
            }
        }.launchIn(viewModelScope)
    }
}