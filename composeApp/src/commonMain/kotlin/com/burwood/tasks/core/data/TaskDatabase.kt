package com.burwood.tasks.core.data

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.burwood.tasks.tasks.data.database.TaskDao
import com.burwood.tasks.tasks.data.database.TaskEntity
import com.burwood.tasks.tasks.data.database.TaskListDao
import com.burwood.tasks.tasks.data.database.TaskListEntity
import kotlinx.coroutines.Dispatchers


@Database(entities = [TaskEntity::class, TaskListEntity::class], version = 1)
@ConstructedBy(TaskDatabaseConstructor::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun taskListDao(): TaskListDao
}


const val DATABASE_NAME = "task_database.db"


@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object TaskDatabaseConstructor : RoomDatabaseConstructor<TaskDatabase> {
    override fun initialize(): TaskDatabase
}


fun getRoomDatabase(
    builder: RoomDatabase.Builder<TaskDatabase>
): TaskDatabase = builder
    .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = false)
    .setQueryCoroutineContext(Dispatchers.Default)
    .build()
