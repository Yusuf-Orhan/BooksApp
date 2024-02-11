package com.yusuforhan.booksapp.android.domain.repository

import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.data.model.remote.CrudResponse
import com.yusuforhan.booksapp.android.data.model.remote.FavoriteModel

interface FavoriteRepository {

    suspend fun addToFavorites(favoriteModel: FavoriteModel) : CrudResponse
    suspend fun getFavorites() : BooksModel
    suspend fun deleteFavorite(favoriteModel: FavoriteModel) : CrudResponse
}