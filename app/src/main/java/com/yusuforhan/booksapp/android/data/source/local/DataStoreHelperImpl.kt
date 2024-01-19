package com.yusuforhan.booksapp.android.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.yusuforhan.booksapp.android.domain.source.local.DataStoreHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app-state")

class DataStoreHelperImpl @Inject constructor(
    private val context: Context
) : DataStoreHelper {

    private val dataStore = context.dataStore
    val IS_LOGIN = booleanPreferencesKey("isLogin")

    override suspend fun saveLoginState(state: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGIN] = state
        }

    }

    override suspend fun getLoginState(): Flow<Boolean?> {
        val isLogin: Flow<Boolean> = dataStore.data.map {
            it[IS_LOGIN] ?: false
        }
        return isLogin
    }
}