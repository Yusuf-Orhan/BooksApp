package com.yusuforhan.booksapp.android.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.domain.usecase.GetAllBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllBooksUseCase: GetAllBooksUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state

    init {
        getAllBooks()
    }

    private fun getAllBooks() {
        getAllBooksUseCase().onEach { result ->
            when (result) {
                Resource.Loading -> _state.value = _state.value.copy(loading = true)
                is Resource.Success -> _state.value =
                    _state.value.copy(loading = false, books = result.data.books)

                is Resource.Error -> _state.value = _state.value.copy(
                    loading = false,
                    error = result.throwable.localizedMessage.orEmpty()
                )
            }
        }.launchIn(viewModelScope)
    }
}