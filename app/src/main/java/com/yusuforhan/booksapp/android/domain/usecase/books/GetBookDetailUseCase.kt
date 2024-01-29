package com.yusuforhan.booksapp.android.domain.usecase.books

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.Books
import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBookDetailUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    operator fun invoke(id : Int): Flow<Resource<Books>> = flow {
        emit(Resource.Loading)
        runCatching {
            booksRepository.getBookDetail(id)
        }.onSuccess {
            emit(Resource.Success(it))

        }.onFailure {
            emit(Resource.Error(it.message ?: "Exception!"))
        }
    }
}