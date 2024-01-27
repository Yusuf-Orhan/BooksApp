package com.yusuforhan.booksapp.android.domain.repository

import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.data.model.remote.CartModel
import com.yusuforhan.booksapp.android.data.model.remote.CategoriesModel
import com.yusuforhan.booksapp.android.data.model.remote.CrudResponse

interface CartRepository {
    suspend fun addToCart(cartModel: CartModel) : CrudResponse
    suspend fun deleteFromCart(cartModel: CartModel) : CrudResponse
    suspend fun getCartBooks(userId : String) : BooksModel
    suspend fun clearCart() : CrudResponse
}