package com.yusuforhan.booksapp.android.common

import android.content.Context
import android.widget.Toast
import com.yusuforhan.booksapp.android.data.model.remote.Book
import java.text.DecimalFormat

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun List<Book>.calculateTotalPrice(): String {
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(this.sumOf { it.price })
}