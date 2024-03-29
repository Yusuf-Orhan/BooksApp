package com.yusuforhan.booksapp.android.domain.usecase.auth

import com.yusuforhan.booksapp.android.domain.repository.AuthRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = authRepository.signOut()
}