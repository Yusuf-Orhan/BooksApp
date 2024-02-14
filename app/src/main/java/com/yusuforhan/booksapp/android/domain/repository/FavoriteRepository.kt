package com.yusuforhan.booksapp.android.domain.repository

import com.yusuforhan.booksapp.android.data.model.local.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun addToFavorites(favoriteEntity: FavoriteEntity)
    suspend fun getFavorites() : Flow<List<FavoriteEntity>>
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)
}