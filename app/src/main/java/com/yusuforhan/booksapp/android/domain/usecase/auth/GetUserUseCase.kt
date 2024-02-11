package com.yusuforhan.booksapp.android.domain.usecase.auth

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.domain.repository.AuthRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    operator fun invoke(userId : String) = flow {
        emit(Resource.Loading)
        runCatching {
            authRepository.getUserById(userId)
        }.onSuccess {
            println("Use Case Name" + it.user.name)
            println("Response : " + it.status)
            emit(Resource.Success(it))
        }.onFailure {
            println("Use Case Exception" + it.localizedMessage)
            emit(Resource.Error(it.message.orEmpty()))
        }
    }
}