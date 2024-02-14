package com.yusuforhan.booksapp.android.domain.usecase.favorite

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.local.FavoriteEntity
import com.yusuforhan.booksapp.android.data.model.remote.FavoriteModel
import com.yusuforhan.booksapp.android.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke(favoriteEntity: FavoriteEntity) = flow {
        emit(favoriteRepository.addToFavorites(favoriteEntity))
    }
}