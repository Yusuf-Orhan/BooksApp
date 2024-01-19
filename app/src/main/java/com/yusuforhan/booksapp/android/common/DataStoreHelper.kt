package com.yusuforhan.booksapp.android.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStoreHelper(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app-data")

    companion object {
        val IS_LOGIN = booleanPreferencesKey("is_login")
    }

    fun setData(isLogin : Boolean) = CoroutineScope(Dispatchers.IO).launch {
        context.dataStore.edit { data ->
            data[IS_LOGIN] = isLogin
        }
    }
    val isLogin : Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_LOGIN] ?: true
        }

}