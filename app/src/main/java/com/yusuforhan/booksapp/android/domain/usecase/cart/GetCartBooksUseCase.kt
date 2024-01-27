package com.yusuforhan.booksapp.android.domain.usecase.cart

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import com.yusuforhan.booksapp.android.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCartBooksUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    operator fun invoke(userId : String): Flow<Resource<BooksModel>> = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(cartRepository.getCartBooks(userId)))
        } catch (e: HttpException) {
            emit(Resource.Error(e))
        } catch (e: IOException) {
            emit(Resource.Error(e))
        }
    }
}