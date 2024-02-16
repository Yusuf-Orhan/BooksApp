package com.yusuforhan.booksapp.android.domain.usecase.books

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.Book
import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBookDetailUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    operator fun invoke(id : Int): Flow<Resource<Book>> = flow {
        emit(Resource.Loading)
        runCatching {
            booksRepository.getBookDetail(id)
        }.onSuccess {
            emit(Resource.Success(it.product))
        }.onFailure {
            emit(Resource.Error(it.message.orEmpty()))
        }
    }
}