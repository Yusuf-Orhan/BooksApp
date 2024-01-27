package com.yusuforhan.booksapp.android.domain.usecase.books

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.Books
import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllBooksUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {

    operator fun invoke(): Flow<Resource<List<Books>>> = flow {
        emit(Resource.Loading)
        val response = booksRepository.getAllBooks()
        if(response.status == 200) {
            emit(Resource.Success(response.books))
        }else {
            emit(Resource.Error(response.message))
        }
    }
}