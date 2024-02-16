package com.yusuforhan.booksapp.android.domain.usecase.books

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.Book
import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSaleBookListUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {

    operator fun invoke(): Flow<Resource<List<Book>>> = flow {
        emit(Resource.Loading)
        runCatching {
            booksRepository.getSaleBooks()
        }.onSuccess {
            emit(Resource.Success(it.books))

        }.onFailure {
            emit(Resource.Error(it.message ?: "Exception!"))
        }
    }
}