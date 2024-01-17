package com.yusuforhan.booksapp.android.domain.source

import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
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
}