package com.yusuforhan.booksapp.android.data.model.remote

import com.google.gson.annotations.SerializedName

data class BooksModel(
    val message: String,
    @SerializedName("products")
    val books : List<Books>,
    val status: Int
)