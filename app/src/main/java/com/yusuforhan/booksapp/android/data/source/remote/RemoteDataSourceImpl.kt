package com.yusuforhan.booksapp.android.data.source.remote

import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
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

    override suspend fun searchBooks(query: String): BooksModel = authService.searchBooks(query)

    override suspend fun getSaleBooksList(): BooksModel = authService.getSaleBooksList()
}