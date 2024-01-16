package com.yusuforhan.booksapp.android.domain.usecase

import androidx.compose.ui.res.booleanResource
import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class GetAllBooksUseCase @Inject constructor(
    private val booksRepository: BooksRepository
){
    val booksList = MutableStateFlow<List<BooksModel>>(emptyList())

    suspend operator fun invoke() {
        booksList.value = booksRepository.getAllBooks()
    }
}