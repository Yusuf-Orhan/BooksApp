package com.yusuforhan.booksapp.android.domain.usecase.auth

import androidx.compose.animation.core.infiniteRepeatable
import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.UserModel
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
            emit(Resource.Success(it))
        }.onFailure {
            emit(Resource.Error(it.message.orEmpty()))
        }
    }
}