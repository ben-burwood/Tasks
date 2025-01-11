package com.burwood.tasks.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath


internal const val DATASTORE_NAME = "settings.preferences_pb"


fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )


/** Read a value from the DataStore for the given Key
 * @param item The DataStoreItem (Key) to read
 * @return The value of the DataStoreItem (Key) from the DataStore */
fun <T> DataStore<Preferences>.readAsFlow(item: DataStoreItem<T>): Flow<T?> {
    return this.data.map { preferences ->
        preferences[item.key]
    }
}

/** Read a value from the DataStore for the given Key
 * @param item The DataStoreItem (Key) to read
 * @return The value of the DataStoreItem (Key) from the DataStore */
suspend fun <T> DataStore<Preferences>.read(item: DataStoreItem<T>): T? {
    return this.readAsFlow(item).first()
}


/** Write a value to the DataStore for the given Key
 * @param item The DataStoreItem (Key) to write
 * @param value The value to write to the DataStoreItem (Key) */
suspend fun <T> DataStore<Preferences>.write(item: DataStoreItem<T>, value: T) {
    this.edit { preferences ->
        preferences[item.key] = value
    }
}


/** Remove a value from the DataStore for the given Key
 * @param item The DataStoreItem (Key) to remove */
suspend fun DataStore<Preferences>.remove(item: DataStoreItem<*>) {
    this.edit { preferences ->
        preferences.remove(item.key)
    }
}


/** Clear all values from the DataStore */
suspend fun DataStore<Preferences>.clear() = this.edit { it.clear() }