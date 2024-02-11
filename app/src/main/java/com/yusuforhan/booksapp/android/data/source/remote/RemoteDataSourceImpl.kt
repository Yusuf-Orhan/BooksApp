package com.yusuforhan.booksapp.android.data.source.remote

import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.data.model.remote.CartModel
import com.yusuforhan.booksapp.android.data.model.remote.DeleteCartModel
import com.yusuforhan.booksapp.android.data.model.remote.SignInModel
import com.yusuforhan.booksapp.android.data.model.remote.SignUpModel
import com.yusuforhan.booksapp.android.domain.source.remote.RemoteDataSource
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : RemoteDataSource {
    override suspend fun signIn(signInModel: SignInModel) = authService.signIn(signInModel)

    override suspend fun signUp(signUpModel: SignUpModel) = authService.signUp(signUpModel)
    override suspend fun getAllBooks(): BooksModel = authService.getAllBooks()

    override suspend fun searchBooks(query: String): BooksModel = authService.searchBooks(query = query)

    override suspend fun getSaleBooksList(): BooksModel = authService.getSaleBooksList()
    override suspend fun getBooksByCategory(category: String) =
        authService.getBooksByCategory(category = category)

    override suspend fun getCartBooks(userId: String) = authService.getCartBooks(userId = userId)

    override suspend fun addToCart(cartModel: CartModel) =
        authService.addToCart(cartModel = cartModel)

    override suspend fun deleteFromCart(cartModel: DeleteCartModel) =
        authService.deleteFromCart(cartModel = cartModel)

    override suspend fun clearCart() = authService.clearCart()
    override suspend fun getCategories() = authService.getCategories()
    override suspend fun getUserById(userId: String) = authService.getUserById(userId = userId)

    override suspend fun getBookDetail(id : Int) = authService.getBookDetail(id = id)
}