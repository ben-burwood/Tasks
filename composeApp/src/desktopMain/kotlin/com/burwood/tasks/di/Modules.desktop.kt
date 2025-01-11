package com.burwood.tasks.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.burwood.tasks.core.data.HttpClientFactory
import com.burwood.tasks.core.data.TaskDatabase
import com.burwood.tasks.core.data.createDataStore
import com.burwood.tasks.core.data.getDatabaseBuilder
import com.burwood.tasks.core.data.getRoomDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module


actual val httpModule = module {
    single<HttpClient> {
        HttpClientFactory.create(
            OkHttp.create()
        )
    }
}



actual val taskDatabaseModule = module {
    single<TaskDatabase> {
        getRoomDatabase(
            getDatabaseBuilder()
        )
    }
}