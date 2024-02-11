package com.yusuforhan.booksapp.android.presentation.detail.viewmodel

import com.yusuforhan.booksapp.android.data.model.remote.CartModel
import com.yusuforhan.booksapp.android.data.model.remote.FavoriteModel

sealed class DetailEvent {
    data class AddCart(val cartModel: CartModel) : DetailEvent()

    data class AddToFavorite(val productId : Int,val userId : String? = null) : DetailEvent()
}
