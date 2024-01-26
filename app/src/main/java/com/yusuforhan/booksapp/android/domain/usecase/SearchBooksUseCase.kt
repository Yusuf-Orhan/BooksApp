package com.yusuforhan.booksapp.android.domain.usecase

import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import javax.inject.Inject

class SearchBooksUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {

    suspend operator fun invoke() {

    }
}