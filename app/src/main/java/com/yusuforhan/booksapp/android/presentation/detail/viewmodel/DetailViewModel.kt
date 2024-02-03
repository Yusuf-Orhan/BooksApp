package com.yusuforhan.booksapp.android.presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.CartModel
import com.yusuforhan.booksapp.android.domain.usecase.books.GetBookDetailUseCase
import com.yusuforhan.booksapp.android.domain.usecase.cart.AddToCartUseCase
import com.yusuforhan.booksapp.android.presentation.navigation.Screen.Companion.bookIdKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getBookDetailUseCase: GetBookDetailUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state : StateFlow<DetailState> = _state

    private val id = savedStateHandle.get<Int>(bookIdKey) ?: 0
    init {
        getBookDetail()
    }
    fun handleEvent(event: DetailEvent) {
        when(event) {
            is DetailEvent.AddCart -> addCart(event.cartModel)
        }
    }
    private fun getBookDetail() {
        getBookDetailUseCase(id).onEach { result ->
            when(result) {
                is Resource.Loading -> _state.value = state.value.copy(isLoading = true)
                is Resource.Success -> {
                    _state.value = state.value.copy(isLoading = false, book = result.data)
                }
                is Resource.Error -> _state.value = state.value.copy(isLoading = false, isError = result.message)
            }
        }.launchIn(viewModelScope)
    }
    private fun addCart(cartModel: CartModel) {
        addToCartUseCase(cartModel).onEach {
            if (it) {
                _state.value = state.value.copy(addToCart = true)
            }
        }.launchIn(viewModelScope)
    }
}