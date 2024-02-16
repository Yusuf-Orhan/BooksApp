package com.yusuforhan.booksapp.android.data.model.remote

data class Book(
    val category: String,
    val count: Int,
    val description: String,
    val id: Int,
    val imageOne: String,
    val imageThree: String,
    val imageTwo: String,
    val price: Double,
    val rate: Double,
    val salePrice: Double,
    val saleState: Boolean,
    val title: String
)