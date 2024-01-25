package com.yusuforhan.booksapp.android.data.repository

import com.yusuforhan.booksapp.android.data.model.remote.CartModel
import com.yusuforhan.booksapp.android.domain.repository.CartRepository
import com.yusuforhan.booksapp.android.domain.source.remote.RemoteDataSource
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : CartRepository {
    override suspend fun addToCart(cartModel: CartModel) = remoteDataSource.addToCart(cartModel)

    override suspend fun deleteFromCart(cartModel: CartModel) =
        remoteDataSource.deleteFromCart(cartModel)

    override suspend fun clearCart() = remoteDataSource.clearCart()
}