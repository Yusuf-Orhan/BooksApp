package com.yusuforhan.booksapp.android.presentation.favorite.viewmodel

import com.yusuforhan.booksapp.android.data.model.local.FavoriteEntity

sealed class FavoriteUiEvent {
    data class DeleteFromFavorite(val favoriteEntity: FavoriteEntity) : FavoriteUiEvent()
}
