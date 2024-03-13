package com.yusuforhan.booksapp.android.presentation.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.data.model.remote.SignInModel
import com.yusuforhan.booksapp.android.data.model.remote.SignUpModel
import com.yusuforhan.booksapp.android.domain.usecase.auth.SaveUserIdUseCase
import com.yusuforhan.booksapp.android.domain.usecase.auth.SignInUseCase
import com.yusuforhan.booksapp.android.domain.usecase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val saveIsLoginUseCase: SaveUserIdUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state : StateFlow<AuthState>  = _state

    fun onEvent(event: AuthEvent) {
       when(event) {
           is AuthEvent.SignUp -> {
               signUp(event.signUpModel)
           }
           is AuthEvent.SignIn -> {
               signIn(event.signInModel)
           }
       }
    }
    private fun signUp(signUpModel: SignUpModel) = viewModelScope.launch {
        try {
            val response = signUpUseCase(signUpModel)
            if (signUpModel.address.isEmpty() || signUpModel.email.isEmpty() || signUpModel.name.isEmpty() || signUpModel.password.isEmpty() || signUpModel.phone.isEmpty()) {
                _state.value = state.value.copy(isSuccess = false,emptyParameter = true, message = null)
            } else {
                if (response.status == 200 || response.status == 299) {
                    _state.value = state.value.copy(isSuccess = true, emptyParameter = false, message = response.message,userId = response.userId)
                    saveIsLoginUseCase(response.userId ?: "")
                } else {
                    _state.value = state.value.copy(isSuccess = false, emptyParameter = false, message = response.message, userId = null)
                    println("Signup Message :" + response.message)
                }
            }
        } catch (e : Exception) {
            _state.value = state.value.copy(isSuccess = false)
            println("Signup Exception Message:" + e.message)

        }
    }
    private fun signIn(signInModel: SignInModel) = viewModelScope.launch {
        try {
            val response = signInUseCase(signInModel)
            if (signInModel.email.isEmpty() || signInModel.password.isEmpty()) {
                _state.value = state.value.copy(isSuccess = false,emptyParameter = true, message = null)
            } else {
                if (response.status == 200 || response.status == 299) {
                    _state.value = state.value.copy(isSuccess = true, emptyParameter = false, message = response.message,userId = response.userId)
                    saveIsLoginUseCase(response.userId ?: "")
                } else {
                    _state.value = state.value.copy(isSuccess = false, emptyParameter = false, message = response.message, userId = null)
                    println("SignIn Message :" + response.message)
                }
            }
        } catch (e : Exception) {
            _state.value = state.value.copy(isSuccess = false)
            println("SignIn Exception Message:" + e.message)

        }
    }
}