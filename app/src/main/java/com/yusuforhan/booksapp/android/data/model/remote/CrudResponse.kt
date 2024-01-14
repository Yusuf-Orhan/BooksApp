package com.yusuforhan.booksapp.android.data.model.remote

data class CrudResponse(
    val message: String,
    val status: Int,
    val userId: String?
)