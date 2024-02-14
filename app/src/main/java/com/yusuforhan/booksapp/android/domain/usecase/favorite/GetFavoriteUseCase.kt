package com.yusuforhan.booksapp.android.domain.usecase.favorite

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.Books
import com.yusuforhan.booksapp.android.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    operator fun invoke(userId : String) = flow<Resource<List<Books>>> {
        emit(Resource.Loading)
        runCatching {
            favoriteRepository.getFavorites(userId)
        }.onSuccess {
            println(it.status)
            println(it.message)
            emit(Resource.Success(it.books))
        }.onFailure {
            println(it.message)
            emit(Resource.Error(it.message.orEmpty()))
        }
    }
}