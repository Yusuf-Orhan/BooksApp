package com.yusuforhan.booksapp.android.domain.usecase.auth

import com.yusuforhan.booksapp.android.domain.repository.AuthRepository
import javax.inject.Inject

class SaveUserIdUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userId : String) {
        authRepository.saveIsLogin(userId)
    }
}