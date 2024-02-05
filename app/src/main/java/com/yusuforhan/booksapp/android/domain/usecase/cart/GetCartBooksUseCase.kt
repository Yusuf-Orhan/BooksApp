package com.yusuforhan.booksapp.android.domain.usecase.cart

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.Books
import com.yusuforhan.booksapp.android.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCartBooksUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    operator fun invoke(userId: String): Flow<Resource<List<Books>>> = flow {
        emit(Resource.Loading)
        runCatching {
            cartRepository.getCartBooks(userId)
        }.onSuccess {
            emit(Resource.Success(it.books))
        }.onFailure { e ->
            emit(Resource.Error(e.message.orEmpty()))

        }
    }
}