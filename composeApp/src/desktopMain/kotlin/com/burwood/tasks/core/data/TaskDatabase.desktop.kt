package com.burwood.tasks.core.data

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File


fun getDatabaseBuilder(): RoomDatabase.Builder<TaskDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DATABASE_NAME)
    return Room.databaseBuilder<TaskDatabase>(
        name = dbFile.absolutePath,
    )
}