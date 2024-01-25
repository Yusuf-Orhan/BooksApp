package com.yusuforhan.booksapp.android.data.repository

import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.data.model.remote.CategoriesModel
import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import com.yusuforhan.booksapp.android.domain.source.remote.RemoteDataSource
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : BooksRepository {
    override suspend fun getAllBooks(): BooksModel = dataSource.getAllBooks()
    override suspend fun getSaleBooks(): BooksModel = dataSource.getSaleBooksList()
    override suspend fun searchBooks(query: String): BooksModel = dataSource.searchBooks(query)
    override suspend fun getBooksByCategory(category: String) = dataSource.getBooksByCategory(category)

    override suspend fun getBookDetail(id: Int) = dataSource.getBookDetail(id)

    override suspend fun getCategories() = dataSource.getCategories()
}