package com.yusuforhan.booksapp.android.domain.usecase.favorite

import com.yusuforhan.booksapp.android.data.model.local.FavoriteEntity
import com.yusuforhan.booksapp.android.domain.repository.FavoriteRepository
import javax.inject.Inject

class DeleteFromFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(favoriteEntity: FavoriteEntity) =
        favoriteRepository.deleteFavorite(favoriteEntity)

}