package com.burwood.tasks.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration


private val koinModules = listOf(
    httpModule, dataStoreModule, taskDatabaseModule, dataSourceModule, repositoryModule, viewModelModule
)


fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(koinModules)
    }
}