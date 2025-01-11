package com.burwood.tasks.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import java.io.File


fun createDataStore(): DataStore<Preferences> = createDataStore(
    producePath = {
        val datastoreFile = File(System.getProperty("java.io.tmpdir"), DATASTORE_NAME)
        datastoreFile.absolutePath
    }
)