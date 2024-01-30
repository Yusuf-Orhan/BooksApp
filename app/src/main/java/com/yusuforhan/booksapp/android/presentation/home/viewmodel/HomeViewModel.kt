package com.yusuforhan.booksapp.android.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.domain.usecase.books.GetAllBooksUseCase
import com.yusuforhan.booksapp.android.domain.usecase.books.GetSaleBookListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllBooksUseCase: GetAllBooksUseCase,
    private val getSaleBookListUseCase: GetSaleBookListUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state

    init {
        getAllBooks()
        getSaleBooks()
    }

    private fun getSaleBooks()  {
        getSaleBookListUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = _state.value.copy(loading = true)
                is Resource.Success -> _state.value =
                    _state.value.copy(loading = false, saleBooks = result.data)

                is Resource.Error -> _state.value =
                    _state.value.copy(loading = false, error = result.message)
            }
        }.launchIn(viewModelScope)
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
}