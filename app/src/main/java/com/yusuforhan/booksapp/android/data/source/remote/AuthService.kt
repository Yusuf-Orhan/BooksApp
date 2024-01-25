package com.yusuforhan.booksapp.android.data.source.remote

import com.yusuforhan.booksapp.android.common.Constants.GET_BOOKS
import com.yusuforhan.booksapp.android.common.Constants.GET_BOOKS_BY_CATEGORY
import com.yusuforhan.booksapp.android.common.Constants.GET_SALE_BOOKS
import com.yusuforhan.booksapp.android.common.Constants.SEARCH_BOOKS
import com.yusuforhan.booksapp.android.common.Constants.SIGN_IN
import com.yusuforhan.booksapp.android.common.Constants.SIGN_UP
import com.yusuforhan.booksapp.android.common.Constants.STORE_NAME
import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
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

    @GET()
    suspend fun getCartBooks(
        @Header("store") store: String = STORE_NAME,
        @Query("userId") userId : String
    ) : BooksModel

    @POST
    suspend fun addToCart(
        @Header("store") store: String = STORE_NAME
    )


}