package com.yusuforhan.booksapp.android.data.repository

import com.yusuforhan.booksapp.android.data.model.local.FavoriteEntity
import com.yusuforhan.booksapp.android.data.source.local.FavoriteDao
import com.yusuforhan.booksapp.android.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao : FavoriteDao
) : FavoriteRepository {
    override suspend fun addToFavorites(favoriteEntity: FavoriteEntity) = dao.addToFavorite(favoriteEntity)

    override suspend fun getFavorites(): Flow<List<FavoriteEntity>> = dao.getFavorites()

    override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) = dao.deleteFromFavorite(favoriteEntity)

}