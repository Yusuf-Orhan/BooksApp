package com.yusuforhan.booksapp.android.domain.usecase

import com.yusuforhan.booksapp.android.data.model.remote.CrudResponse
import com.yusuforhan.booksapp.android.data.model.remote.SignInModel
import com.yusuforhan.booksapp.android.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
){

    suspend operator fun invoke(signInModel: SignInModel) : CrudResponse {
        return authRepository.signIn(signInModel)
    }
}