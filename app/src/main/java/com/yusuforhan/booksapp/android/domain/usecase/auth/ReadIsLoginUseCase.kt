package com.yusuforhan.booksapp.android.domain.usecase.auth

import com.yusuforhan.booksapp.android.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReadIsLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
){

    operator fun invoke() : Flow<Boolean> {
        return authRepository.getIsLogin()
    }
}