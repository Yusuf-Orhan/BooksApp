package com.yusuforhan.booksapp.android.domain.usecase.books

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBookDetail @Inject constructor(
    private val booksRepository: BooksRepository
) {
    operator fun invoke(id : Int): Flow<Resource<BooksModel>> = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(booksRepository.getBookDetail(id)))
        } catch (e: HttpException) {
            emit(Resource.Error(e))
        } catch (e: IOException) {
            emit(Resource.Error(e))
        }
    }
}