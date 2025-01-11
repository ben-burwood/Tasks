package com.burwood.tasks.di

import com.burwood.tasks.TasksViewModel
import com.burwood.tasks.tasks.data.TaskListLocalDataSource
import com.burwood.tasks.tasks.data.TaskListRemoteDataSource
import com.burwood.tasks.tasks.data.TaskListRepository
import com.burwood.tasks.tasks.data.TaskLocalDataSource
import com.burwood.tasks.tasks.data.TaskRemoteDataSource
import com.burwood.tasks.tasks.data.TaskRepository
import com.burwood.tasks.tasks.domain.ITaskListRepository
import com.burwood.tasks.tasks.domain.ITaskRepository
import com.burwood.tasks.tasks.domain.TaskDataSource
import com.burwood.tasks.tasks.domain.TaskListDataSource
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


expect val httpModule: Module

expect val taskDatabaseModule: Module


val dataSourceModule = module {
    singleOf(::TaskRemoteDataSource).bind<TaskDataSource>()
    singleOf(::TaskLocalDataSource).bind<TaskDataSource>()
    singleOf(::TaskListRemoteDataSource).bind<TaskListDataSource>()
    singleOf(::TaskListLocalDataSource).bind<TaskListDataSource>()
}

