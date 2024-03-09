package com.yusuforhan.booksapp.android.presentation.profile.viewmodel

import android.text.PrecomputedText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.domain.usecase.auth.GetUserUseCase
import com.yusuforhan.booksapp.android.domain.usecase.auth.ReadUserIdUseCase
import com.yusuforhan.booksapp.android.domain.usecase.auth.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val readUserIdUseCase: ReadUserIdUseCase,
    private val signOutUseCase: SignOutUseCase
): ViewModel() {

    val state = MutableStateFlow(ProfileState())
    init {
        readUserIdUseCase().onEach {
            state.value = state.value.copy(userId = it.orEmpty())
        }.launchIn(viewModelScope)
    }

    fun handleEvent(event: ProfileUiEvent){
        when(event) {
            is ProfileUiEvent.SignOut -> signOut()
        }
    }
    fun getUserById(userId : String) {
        getUserUseCase(userId).onEach {
            when(val response = it) {
                is Resource.Success -> {
                    state.value = state.value.copy(isLoading = false, userModel = response.data)
                }
                is Resource.Error -> {
                    state.value = state.value.copy(isLoading = false, error = response.message)
                }
                is Resource.Loading -> {
                    state.value = state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun signOut() = viewModelScope.launch {
        signOutUseCase()
    }
}