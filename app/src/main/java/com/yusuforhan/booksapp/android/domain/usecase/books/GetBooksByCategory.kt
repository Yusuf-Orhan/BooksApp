package com.yusuforhan.booksapp.android.domain.usecase.books

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.Book
import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBooksByCategory @Inject constructor(
    private val booksRepository: BooksRepository
) {
    operator fun invoke(category : String): Flow<Resource<List<Book>>> = flow {
        emit(Resource.Loading)
        runCatching {
            booksRepository.getBooksByCategory(category)
        }.onSuccess {
            emit(Resource.Success(it.books))

        }.onFailure {
            emit(Resource.Error(it.message ?: "Exception!"))
        }
    }
}