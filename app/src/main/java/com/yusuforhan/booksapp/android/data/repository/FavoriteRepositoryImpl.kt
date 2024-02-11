package com.yusuforhan.booksapp.android.data.repository

import com.yusuforhan.booksapp.android.data.model.remote.FavoriteModel
import com.yusuforhan.booksapp.android.domain.repository.FavoriteRepository
import com.yusuforhan.booksapp.android.domain.source.remote.RemoteDataSource
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : FavoriteRepository {
    override suspend fun addToFavorites(favoriteModel: FavoriteModel) = remoteDataSource.addToFavorite(favoriteModel = favoriteModel)

    override suspend fun getFavorites() = remoteDataSource.getFavorites()

    override suspend fun deleteFavorite(favoriteModel: FavoriteModel) = remoteDataSource.deleteFavorite(favoriteModel = favoriteModel)


}