package com.yusuforhan.booksapp.android.presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.CartModel
import com.yusuforhan.booksapp.android.data.model.remote.FavoriteModel
import com.yusuforhan.booksapp.android.domain.usecase.auth.ReadUserIdUseCase
import com.yusuforhan.booksapp.android.domain.usecase.books.GetBookDetailUseCase
import com.yusuforhan.booksapp.android.domain.usecase.cart.AddToCartUseCase
import com.yusuforhan.booksapp.android.domain.usecase.favorite.AddToFavoriteUseCase
import com.yusuforhan.booksapp.android.presentation.navigation.Screen.Companion.bookIdKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getBookDetailUseCase: GetBookDetailUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val readUserIdUseCase: ReadUserIdUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val addToFavoriteUseCase: AddToFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state : StateFlow<DetailState> = _state

    private var userId = ""
    private val id = savedStateHandle.get<Int>(bookIdKey) ?: 0
    init {
        getBookDetail()
        readUserIdUseCase().onEach {
            userId = it.orEmpty()
            _state.value = state.value.copy(userId = it)
        }.launchIn(viewModelScope)
    }
    fun handleEvent(event: DetailEvent) {
        when(event) {
            is DetailEvent.AddCart -> addCart(event.cartModel)
            is DetailEvent.AddToFavorite -> addToFavorite(FavoriteModel(userId,event.productId))
        }
    }
    private fun getBookDetail() {
        getBookDetailUseCase(id).onEach { result ->
            when(result) {
                is Resource.Loading -> _state.value = state.value.copy(isLoading = true)
                is Resource.Success -> {
                    _state.value = state.value.copy(isLoading = false, book = result.data)
                }
                is Resource.Error -> _state.value = state.value.copy(isLoading = false, isError = result.message)
            }
        }.launchIn(viewModelScope)
    }
    private fun addCart(cartModel: CartModel) {
        addToCartUseCase(cartModel).onEach {
            when(it) {
                is Resource.Success -> _state.value = state.value.copy(addToCart = true)
                is Resource.Error ->  _state.value = state.value.copy(addToCart = false)
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
    private fun addToFavorite(favoriteModel : FavoriteModel) {
        addToFavoriteUseCase(favoriteModel).onEach {
            when(it) {
                is Resource.Success -> {
                    println("Success")
                }
                is Resource.Error -> {
                    println("Error")
                }
                is Resource.Loading -> {
                    println("Loading")
                }
            }
        }.launchIn(viewModelScope)
    }
}