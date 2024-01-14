package com.yusuforhan.booksapp.android.presentation.signup.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.data.model.remote.CrudResponse
import com.yusuforhan.booksapp.android.data.model.remote.SignInModel
import com.yusuforhan.booksapp.android.data.model.remote.SignUpModel
import com.yusuforhan.booksapp.android.domain.source.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val dataSource: RemoteDataSource
) : ViewModel() {
    val state = mutableStateOf(SignUpState())

    fun onEvent(event: SignUpEvent) {
       when(event) {
           is SignUpEvent.SignUp -> {
               signUp(event.signUpModel)
           }
       }
    }
    private fun signUp(signUpModel: SignUpModel) = viewModelScope.launch {
        val response = dataSource.signUp(signUpModel)
        if (signUpModel.address.isEmpty() || signUpModel.email.isEmpty() || signUpModel.name.isEmpty() || signUpModel.password.isEmpty() || signUpModel.phone.isEmpty()) {
            state.value = state.value.copy(isSignup = false,emptyParameter = true, message = null)
        } else {
            if (response.status == 200 || response.status == 299) {
                state.value = state.value.copy(isSignup = true, emptyParameter = false, message = response.message,userId = response.userId)
            } else {
                state.value = state.value.copy(isSignup = false, emptyParameter = false, message = null, userId = null)

            }
        }
    }
}

data class SignUpState(
    val isSignup : Boolean? = null,
    val emptyParameter : Boolean? = null,
    val message : String? = null,
    val userId : String? = null
)
sealed class SignUpEvent {
    data class SignUp(val signUpModel: SignUpModel) : SignUpEvent()

}