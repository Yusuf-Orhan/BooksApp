package com.yusuforhan.booksapp.android.domain.usecase

import com.yusuforhan.booksapp.android.domain.repository.AuthRepository
import javax.inject.Inject

class SaveLoginStateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(loginState: Boolean) {
        authRepository.saveIsLogin(loginState)
    }
}