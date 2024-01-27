package com.yusuforhan.booksapp.android.domain.usecase.auth

import com.yusuforhan.booksapp.android.domain.repository.AuthRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLoginStateUseCase @Inject constructor(
    private val authRepository: AuthRepository
){

    suspend operator fun invoke() = flow {
        authRepository.getIsLogin().collect{
            emit(it)
        }
    }
}