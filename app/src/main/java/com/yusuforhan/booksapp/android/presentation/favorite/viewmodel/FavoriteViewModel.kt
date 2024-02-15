package com.yusuforhan.booksapp.android.presentation.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.domain.usecase.favorite.GetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase
) : ViewModel() {


    init {
        getFavorite()
    }

    private fun getFavorite() = viewModelScope.launch {
        getFavoriteUseCase().collect {
            it.forEach { e ->
                println(e.books.title)
            }
        }
    }

}