package com.yusuforhan.booksapp.android.presentation.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.data.model.local.FavoriteEntity
import com.yusuforhan.booksapp.android.domain.usecase.favorite.DeleteFromFavoriteUseCase
import com.yusuforhan.booksapp.android.domain.usecase.favorite.GetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state: StateFlow<FavoriteState> = _state

    fun handleEvent(event: FavoriteUiEvent) {
        when (event) {
            is FavoriteUiEvent.DeleteFromFavorite -> deleteFromFavorite(event.favoriteEntity)
        }
    }

    init {
        getFavorite()
    }

    private fun getFavorite() = viewModelScope.launch {
        getFavoriteUseCase().collect {
            if (it.isEmpty()) _state.value = state.value.copy(booksEmpty = true)
            else _state.value = state.value.copy(favoriteBooks = it)
        }
    }

    private fun deleteFromFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        deleteFromFavoriteUseCase(favoriteEntity)
    }
}