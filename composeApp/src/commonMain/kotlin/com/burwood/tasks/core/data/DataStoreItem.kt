package com.burwood.tasks.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey


sealed class DataStoreItem<T>(val key: Preferences.Key<T>) {
    data object OATH_TOKEN : DataStoreItem<String>(stringPreferencesKey("oath_token"))

    companion object {
        suspend fun DataStore<Preferences>.getOathToken(): String? = this.read(OATH_TOKEN)
    }
}