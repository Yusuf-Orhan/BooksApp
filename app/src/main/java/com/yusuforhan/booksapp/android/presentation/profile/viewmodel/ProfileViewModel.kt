package com.yusuforhan.booksapp.android.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.domain.usecase.auth.GetUserUseCase
import com.yusuforhan.booksapp.android.domain.usecase.auth.ReadUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val readUserIdUseCase: ReadUserIdUseCase
): ViewModel() {

    val state = MutableStateFlow(ProfileState())
    init {
        readUserIdUseCase().onEach {
            println(it)
            getUserById(userId = it.orEmpty())
        }.launchIn(viewModelScope)
    }
    private fun getUserById(userId : String) {
        println("Get User Fun Started")
        getUserUseCase(userId).onEach {
            println("Get User Fun Resume")
            when(val response = it) {
                is Resource.Success -> {
                    println(response.data.name)
                    state.value = state.value.copy(isLoading = false, userModel = response.data)
                }
                is Resource.Error -> {
                    println(response.message)
                    state.value = state.value.copy(isLoading = false, error = response.message)
                }
                is Resource.Loading -> {
                    state.value = state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}