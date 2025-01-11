package com.burwood.tasks

import android.app.Application
import com.burwood.tasks.di.initKoin
import org.koin.android.ext.koin.androidContext


class TasksApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@TasksApplication)
        }
    }

}