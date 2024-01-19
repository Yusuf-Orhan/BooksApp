package com.yusuforhan.booksapp.android.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.yusuforhan.booksapp.android.domain.source.local.DataStoreHelper
import javax.inject.Inject

class DataStoreHelperImpl @Inject constructor(
    private val context : Context
) : DataStoreHelper {

    // At the top level of your kotlin file:
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app-state")
    override suspend fun saveLoginState(state: Boolean) {
        context.dataStore.edit {

        }
    }

    override suspend fun getLoginState(): Boolean? {
        TODO("Not yet implemented")
    }
}