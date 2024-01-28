package com.yusuforhan.booksapp.android.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
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

    override suspend fun saveUserId(userId : String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_ID] = userId
        }

    }

    override fun readUserId(): Flow<String?> {
        return context.dataStore.data.map {
            it[PreferencesKeys.USER_ID]
        }
    }
}
private object PreferencesKeys {
    val USER_ID = stringPreferencesKey(name = Constants.USER_ID)
}