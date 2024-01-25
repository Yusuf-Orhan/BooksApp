package com.yusuforhan.booksapp.android.data.source.remote

import com.yusuforhan.booksapp.android.common.Constants
import com.yusuforhan.booksapp.android.common.Constants.ADD_TO_CART
import com.yusuforhan.booksapp.android.common.Constants.CLEAR_CART
import com.yusuforhan.booksapp.android.common.Constants.DELETE_FROM_CART
import com.yusuforhan.booksapp.android.common.Constants.GET_BOOKS
import com.yusuforhan.booksapp.android.common.Constants.GET_BOOKS_BY_CATEGORY
import com.yusuforhan.booksapp.android.common.Constants.GET_BOOKS_DETAIL
import com.yusuforhan.booksapp.android.common.Constants.GET_CART_BOOKS
import com.yusuforhan.booksapp.android.common.Constants.GET_CATEGORIES
import com.yusuforhan.booksapp.android.common.Constants.GET_SALE_BOOKS
import com.yusuforhan.booksapp.android.common.Constants.SEARCH_BOOKS
import com.yusuforhan.booksapp.android.common.Constants.SIGN_IN
import com.yusuforhan.booksapp.android.common.Constants.SIGN_UP
import com.yusuforhan.booksapp.android.common.Constants.STORE_NAME
import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.data.model.remote.CartModel
import com.yusuforhan.booksapp.android.data.model.remote.CategoriesModel
import com.yusuforhan.booksapp.android.data.model.remote.CrudResponse
import com.yusuforhan.booksapp.android.data.model.remote.SignInModel
import com.yusuforhan.booksapp.android.data.model.remote.SignUpModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST(SIGN_UP)
    suspend fun signUp(@Body signUpModel: SignUpModel): CrudResponse

    @POST(SIGN_IN)
    suspend fun signIn(@Body signInModel: SignInModel): CrudResponse

    @GET(GET_BOOKS)
    suspend fun getAllBooks(
        @Header("store") store: String = STORE_NAME
    ): BooksModel

    @GET(SEARCH_BOOKS)
    suspend fun searchBooks(
        @Header("store") query: String
    ): BooksModel

    @GET(GET_SALE_BOOKS)
    suspend fun getSaleBooksList(
        @Header("store") store: String = STORE_NAME
    ): BooksModel

    @GET(GET_BOOKS_BY_CATEGORY)
    suspend fun getBooksByCategory(
        @Header("store") store: String = STORE_NAME,
        @Query("category") category: String
    ) : BooksModel

    @GET(GET_CART_BOOKS)
    suspend fun getCartBooks(
        @Header("store") store: String = STORE_NAME,
        @Query("userId") userId : String
    ) : BooksModel

    @POST(ADD_TO_CART)
    suspend fun addToCart(
        @Header("store") store: String = STORE_NAME,
        @Body cartModel: CartModel
    ) : CrudResponse
    @POST(DELETE_FROM_CART)
    suspend fun deleteFromCart(
        @Header("store") store: String = STORE_NAME,
        @Body cartModel: CartModel
    ) : CrudResponse
    @POST(CLEAR_CART)
    suspend fun clearCart(
        @Header("store") store: String = STORE_NAME,
        @Body cartModel: CartModel
    ) : CrudResponse


    @GET(GET_CATEGORIES)
    suspend fun getCategories(
        @Header("store") store: String = STORE_NAME
    ) : CategoriesModel

    @GET(GET_BOOKS_DETAIL)
    suspend fun getBookDetail(
        @Header("store") store: String = STORE_NAME
    ) : CategoriesModel

}