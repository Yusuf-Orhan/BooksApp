package com.yusuforhan.booksapp.android.presentation.favorite.viewmodel

import com.yusuforhan.booksapp.android.data.model.local.FavoriteEntity

data class FavoriteState(
    val favoriteBooks : List<FavoriteEntity> = listOf(),
    val booksEmpty : Boolean? = null
)