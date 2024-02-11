package com.yusuforhan.booksapp.android.domain.usecase.books

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.Books
import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchBooksUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    operator fun invoke(query : String): Flow<Resource<List<Books>>> = flow {
        emit(Resource.Loading)
        runCatching {
            booksRepository.searchBooks(query)
        }.onSuccess {
            emit(Resource.Success(it.books))

        }.onFailure {
            emit(Resource.Error(it.message ?: "Exception!"))
        }
    }
}
