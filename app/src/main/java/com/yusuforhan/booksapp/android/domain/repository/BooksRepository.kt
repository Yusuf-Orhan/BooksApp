package com.yusuforhan.booksapp.android.domain.repository

import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.data.model.remote.CategoriesModel
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun getAllBooks(): BooksModel
    suspend fun getSaleBooks(): BooksModel
    suspend fun searchBooks(query: String): BooksModel
    suspend fun getBooksByCategory(category: String): BooksModel
    suspend fun getBookDetail(id: Int): BooksModel
    suspend fun getCategories() : CategoriesModel
}