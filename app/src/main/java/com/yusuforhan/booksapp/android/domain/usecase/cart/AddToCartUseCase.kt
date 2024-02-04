package com.yusuforhan.booksapp.android.domain.usecase.cart

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.data.model.remote.CartModel
import com.yusuforhan.booksapp.android.data.model.remote.CrudResponse
import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import com.yusuforhan.booksapp.android.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    operator fun invoke(cartModel : CartModel): Flow<Resource<Boolean>> = flow {
        val response = cartRepository.addToCart(cartModel)

        if (response.status == 200) {
            emit(Resource.Success(true))
        }else{
            emit(Resource.Error(response.message))
        }
    }
}