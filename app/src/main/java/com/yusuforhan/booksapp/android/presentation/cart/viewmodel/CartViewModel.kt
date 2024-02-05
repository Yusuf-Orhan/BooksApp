package com.yusuforhan.booksapp.android.presentation.cart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.CartModel
import com.yusuforhan.booksapp.android.data.model.remote.DeleteCartModel
import com.yusuforhan.booksapp.android.domain.usecase.auth.ReadUserIdUseCase
import com.yusuforhan.booksapp.android.domain.usecase.cart.AddToCartUseCase
import com.yusuforhan.booksapp.android.domain.usecase.cart.DeleteFromCartUseCase
import com.yusuforhan.booksapp.android.domain.usecase.cart.GetCartBooksUseCase
import com.yusuforhan.booksapp.android.presentation.home.viewmodel.HomeUiState
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

    private var _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state

    private var userId : String? = null
    fun handleEvent(event: CartUiEvent) {
        when(event) {
            is CartUiEvent.DeleteCart -> {deleteCart(event.id)}
        }
    }
    init {
        readUserIdUseCase().onEach {userId ->
            this.userId = userId
            getCartBooks(userId.orEmpty())
        }.launchIn(viewModelScope)
    }
    private fun getCartBooks(userId : String) {
        getCartBooksUseCase(userId).onEach {
            when(it) {
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

    private fun deleteCart(id : Int) {
        deleteCartUseCase(DeleteCartModel(id,userId!!)).onEach {
            if (it) {
                getCartBooks(userId!!)
            }
        }
    }
}