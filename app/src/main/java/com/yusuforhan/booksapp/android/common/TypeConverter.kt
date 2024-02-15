package com.yusuforhan.booksapp.android.common

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.yusuforhan.booksapp.android.data.model.remote.Books

class TypeConverter  {
    private val gson = Gson()

    @TypeConverter
    fun fromBooks(item : Books) : String {
        return gson.toJson(item)
    }

    @TypeConverter
    fun toBooks(itemString: String) : Books {
        return gson.fromJson(itemString,Books::class.java)
    }
}