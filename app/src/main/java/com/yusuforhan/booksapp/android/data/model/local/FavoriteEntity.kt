package com.yusuforhan.booksapp.android.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yusuforhan.booksapp.android.data.model.remote.Books

@Entity(tableName = "favorite")
data class FavoriteEntity(
    val category: String,
    val count: Int,
    val description: String,
    @PrimaryKey val id: Int,
    val imageOne: String,
    val imageThree: String,
    val imageTwo: String,
    val price: Double,
    val rate: Double,
    val salePrice: Double,
    val saleState: Boolean,
    val title: String
)