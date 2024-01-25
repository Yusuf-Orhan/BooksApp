package com.yusuforhan.booksapp.android.domain.source.remote

import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.data.model.remote.CartModel
import com.yusuforhan.booksapp.android.data.model.remote.CategoriesModel
import com.yusuforhan.booksapp.android.data.model.remote.CrudResponse
import com.yusuforhan.booksapp.android.data.model.remote.SignInModel
import com.yusuforhan.booksapp.android.data.model.remote.SignUpModel
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun signIn(signInModel: SignInModel) : CrudResponse
    suspend fun signUp(signUpModel: SignUpModel) : CrudResponse
    suspend fun getAllBooks() : BooksModel
    suspend fun searchBooks(query : String) : BooksModel
    suspend fun getSaleBooksList() : BooksModel
    suspend fun getBooksByCategory(category : String) : BooksModel
    suspend fun getCartBooks(userId : String) : BooksModel
    suspend fun addToCart(cartModel: CartModel) : CrudResponse
    suspend fun deleteFromCart(cartModel: CartModel) : CrudResponse
    suspend fun clearCart() : CrudResponse
    suspend fun getCategories() : CategoriesModel
    suspend fun getBookDetail(id : Int) : BooksModel

}