package com.yusuforhan.booksapp.android.data.model.remote

data class UserModel(
    val user : User,
    val status : Int,
    val message : String
)

data class User(
    val email: String,
    val name: String,
    val phone: String,
    val userId: String,
    val status : Int,
    val message : String
)