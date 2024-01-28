package com.yusuforhan.booksapp.android.domain.usecase.auth

import com.yusuforhan.booksapp.android.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadUserIdUseCase @Inject constructor(
    private val authRepository: AuthRepository
){

    operator fun invoke() : Flow<String?> {
        return authRepository.getIsLogin()
    }
}