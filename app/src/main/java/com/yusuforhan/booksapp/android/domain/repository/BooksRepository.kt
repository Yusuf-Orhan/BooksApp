package com.yusuforhan.booksapp.android.domain.repository

import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun getAllBooks() : BooksModel

    suspend fun getSaleBooks() : BooksModel

    suspend fun searchBooks(query: String) : BooksModel
}