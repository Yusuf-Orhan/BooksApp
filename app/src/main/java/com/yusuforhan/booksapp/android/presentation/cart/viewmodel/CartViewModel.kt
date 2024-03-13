package com.yusuforhan.booksapp.android.presentation.cart.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.common.Constants
import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.DeleteCartModel
import com.yusuforhan.booksapp.android.domain.usecase.auth.ReadUserIdUseCase
import com.yusuforhan.booksapp.android.domain.usecase.cart.DeleteFromCartUseCase
import com.yusuforhan.booksapp.android.domain.usecase.cart.GetCartBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartBooksUseCase: GetCartBooksUseCase,
    private val deleteCartUseCase: DeleteFromCartUseCase,
    private val readUserIdUseCase: ReadUserIdUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(CartUiState())
    val state: StateFlow<CartUiState> = _state

    private var userId: String? = null
    fun handleEvent(event: CartUiEvent) {

        when (event) {
            is CartUiEvent.DeleteCart -> {
                deleteCart(event.id)
            }
        }
    }

    init {
        readUserIdUseCase().onEach { userId ->
            this.userId = userId
            _state.value = _state.value.copy(userId = userId)
        }.launchIn(viewModelScope)
    }

    fun getCartBooks(userId: String) {
        getCartBooksUseCase(userId).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = state.value.copy(loading = false, books = it.data)
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(loading = false, error = it.message)
                }

                is Resource.Loading -> {
                    _state.value = state.value.copy(loading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteCart(id: Int) {
        deleteCartUseCase(DeleteCartModel(userId!!, id)).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    getCartBooks(userId!!)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}