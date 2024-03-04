package com.yusuforhan.booksapp.android.data.model.remote

data class CategoriesModel(
    val categories: List<Category>,
    val message: String,
    val status: Int
)

data class Category(
    val name: String,
    val image: String?
)