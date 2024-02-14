package com.yusuforhan.booksapp.android.domain.usecase.favorite

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.Books
import com.yusuforhan.booksapp.android.domain.repository.FavoriteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    operator fun invoke() = flow {
        favoriteRepository.getFavorites().onEach {
            emit(it)
        }
    }
}