package com.yusuforhan.booksapp.android.data.repository

import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import com.yusuforhan.booksapp.android.domain.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : BooksRepository {
    override suspend fun getAllBooks(): List<BooksModel> = dataSource.getAllBooks()

    override suspend fun getSaleBooks(): Flow<List<BooksModel>> = dataSource.getSaleBooksList()
    override suspend fun searchBooks(query: String): List<BooksModel> = dataSource.searchBooks(query)
}