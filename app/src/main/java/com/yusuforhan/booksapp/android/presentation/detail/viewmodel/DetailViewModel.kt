package com.yusuforhan.booksapp.android.presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.domain.usecase.books.GetBookDetailUseCase
import com.yusuforhan.booksapp.android.presentation.navigation.Screen.Companion.bookIdKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getBookDetailUseCase: GetBookDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private fun getBookDetail(id : Int) {
        getBookDetailUseCase(checkNotNull(savedStateHandle[bookIdKey])).onEach { result ->
            when(result) {
                is Resource.Loading -> {}
                is Resource.Success -> {}
                is Resource.Error -> {}
            }
        }.launchIn(viewModelScope)
    }

}