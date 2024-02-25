package com.yusuforhan.booksapp.android.presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.local.FavoriteEntity
import com.yusuforhan.booksapp.android.data.model.remote.CartModel
import com.yusuforhan.booksapp.android.domain.usecase.auth.ReadUserIdUseCase
import com.yusuforhan.booksapp.android.domain.usecase.books.GetBookDetailUseCase
import com.yusuforhan.booksapp.android.domain.usecase.cart.AddToCartUseCase
import com.yusuforhan.booksapp.android.domain.usecase.favorite.AddToFavoriteUseCase
import com.yusuforhan.booksapp.android.domain.usecase.favorite.DeleteFromFavoriteUseCase
import com.yusuforhan.booksapp.android.domain.usecase.favorite.GetFavoriteUseCase
import com.yusuforhan.booksapp.android.presentation.navigation.Screen.Companion.bookIdKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getBookDetailUseCase: GetBookDetailUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val readUserIdUseCase: ReadUserIdUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state

    private var userId = ""
    private val id = savedStateHandle.get<Int>(bookIdKey) ?: 0

    init {
        getBookDetail()
        readUserIdUseCase().onEach {
            userId = it.orEmpty()
            _state.value = state.value.copy(userId = it)
        }.launchIn(viewModelScope)
        existsFavorite(id)

    }

    fun existsFavorite(id: Int) = viewModelScope.launch {
        getFavoriteUseCase().collect { favoriteList ->
            val exists = favoriteList.find { it.id == id }
            if (exists != null) {
                _state.value = state.value.copy(isFavorite = true)
            }
        }
    }

    fun handleEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.AddCart -> addCart(event.cartModel)
            is DetailEvent.AddToFavorite -> setFavoriteState(event.favoriteEntity)
        }
    }

    private fun getBookDetail() {
        getBookDetailUseCase(id).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = state.value.copy(isLoading = true)
                is Resource.Success -> {
                    _state.value = state.value.copy(isLoading = false, book = result.data)
                }

                is Resource.Error -> _state.value =
                    state.value.copy(isLoading = false, isError = result.message)

            }
        }.launchIn(viewModelScope)
    }

    private fun addCart(cartModel: CartModel) {
        addToCartUseCase(cartModel).onEach {
            when (it) {
                is Resource.Success -> _state.value = state.value.copy(addToCart = true)
                is Resource.Error -> _state.value = state.value.copy(addToCart = false)
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun addToFavorite(favoriteEntity: FavoriteEntity) {
        addToFavoriteUseCase(favoriteEntity).launchIn(viewModelScope)
        _state.value = state.value.copy(isFavorite = true)
    }

    private fun deleteFromFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        deleteFromFavoriteUseCase(favoriteEntity)
        _state.value = state.value.copy(isFavorite = false)
    }

    private fun setFavoriteState(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        getFavoriteUseCase().collect { favoriteList ->
            val exists = favoriteList.find { it.id == favoriteEntity.id }
            if (exists != null) {
                deleteFromFavorite(favoriteEntity)
            } else {
                addToFavorite(favoriteEntity)
            }
        }
    }
}