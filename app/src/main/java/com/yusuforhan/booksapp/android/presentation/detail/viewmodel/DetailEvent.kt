package com.yusuforhan.booksapp.android.presentation.detail.viewmodel

import com.yusuforhan.booksapp.android.data.model.remote.CartModel

sealed class DetailEvent {
    data class AddCart(val cartModel: CartModel) : DetailEvent()
    data object AddFavorite : DetailEvent()

}
