package com.yusuforhan.booksapp.android.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.yusuforhan.booksapp.android.common.Constants
import com.yusuforhan.booksapp.android.domain.source.local.DataStoreHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app-state")

class DataStoreHelperImpl @Inject constructor(
    private val context: Context
) : DataStoreHelper {

    override suspend fun saveLoginState(isLogin : Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_LOGIN] = isLogin
        }

    }

    override fun readLoginState(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[PreferencesKeys.IS_LOGIN] ?: false
        }
    }
}
private object PreferencesKeys {
    val IS_LOGIN = booleanPreferencesKey(name = Constants.IS_LOGIN)
}