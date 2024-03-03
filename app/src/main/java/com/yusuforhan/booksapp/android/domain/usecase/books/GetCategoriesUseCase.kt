package com.yusuforhan.booksapp.android.domain.usecase.books

import com.yusuforhan.booksapp.android.common.Resource
import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.data.model.remote.CategoriesModel
import com.yusuforhan.booksapp.android.data.model.remote.Category
import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {

    operator fun invoke(): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading)
        runCatching {
            booksRepository.getCategories()
        }.onSuccess {
            emit(Resource.Success(it.categories))
            it.categories.forEach {
                println("Categories : $it")
            }
        }.onFailure {
            emit(Resource.Error(it.message ?: "Exception!"))
            println("Category Error : ${it.message} ")
        }
    }
}