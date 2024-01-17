package com.yusuforhan.booksapp.android.data.model.remote

data class Books(
    val category: String,
    val count: Int,
    val description: String,
    val id: Int,
    val imageOne: String,
    val imageThree: String,
    val imageTwo: String,
    val price: Int,
    val rate: Int,
    val salePrice: Int,
    val saleState: Boolean,
    val title: String
)