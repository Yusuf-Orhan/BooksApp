package com.yusuforhan.booksapp.android.presentation.auth.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.data.model.remote.SignInModel
import com.yusuforhan.booksapp.android.data.model.remote.SignUpModel
import com.yusuforhan.booksapp.android.domain.source.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dataSource: RemoteDataSource
) : ViewModel() {
    val state = mutableStateOf(AuthState())

    fun onEvent(event: SignUpEvent) {
       when(event) {
           is SignUpEvent.SignUp -> {
               state.value = state.value.copy(null,null,null,null)
               signUp(event.signUpModel)
           }
       }
    }
    private fun signUp(signUpModel: SignUpModel) = viewModelScope.launch {
        try {
            val response = dataSource.signUp(signUpModel)
            if (signUpModel.address.isEmpty() || signUpModel.email.isEmpty() || signUpModel.name.isEmpty() || signUpModel.password.isEmpty() || signUpModel.phone.isEmpty()) {
                state.value = state.value.copy(isSuccess = false,emptyParameter = true, message = null)
            } else {
                if (response.status == 200 || response.status == 299) {
                    state.value = state.value.copy(isSuccess = true, emptyParameter = false, message = response.message,userId = response.userId)
                } else {
                    state.value = state.value.copy(isSuccess = false, emptyParameter = false, message = null, userId = null)

                }
            }
        } catch (e : Exception) {
            state.value = state.value.copy(isSuccess = false)
        }
    }
    private fun signIn(signInModel: SignInModel) = viewModelScope.launch {
        try {
            val response = dataSource.signIn(signInModel)
            if (signInModel.email.isEmpty() || signInModel.password.isEmpty()) {
                state.value = state.value.copy(isSuccess = false,emptyParameter = true, message = null)
            } else {
                if (response.status == 200 || response.status == 299) {
                    state.value = state.value.copy(isSuccess = true, emptyParameter = false, message = response.message,userId = response.userId)
                } else {
                    state.value = state.value.copy(isSuccess = false, emptyParameter = false, message = null, userId = null)

                }
            }
        } catch (e : Exception) {
            state.value = state.value.copy(isSuccess = false)
        }
    }
}