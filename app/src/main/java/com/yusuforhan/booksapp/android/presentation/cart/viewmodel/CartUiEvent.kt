package com.yusuforhan.booksapp.android.presentation.cart.viewmodel

import com.yusuforhan.booksapp.android.data.model.remote.CartModel

sealed class CartUiEvent {
    data class DeleteCart(val id: Int) : CartUiEvent()
}
