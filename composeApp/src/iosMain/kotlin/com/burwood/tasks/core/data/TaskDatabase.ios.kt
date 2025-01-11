package com.burwood.tasks.core.data

import androidx.room.Room
import androidx.room.RoomDatabase


fun getDatabaseBuilder(): RoomDatabase.Builder<TaskDatabase> {
    val dbFilePath = documentDirectory() + "/$DATABASE_NAME"
    return Room.databaseBuilder<TaskDatabase>(
        name = dbFilePath,
    )
}


private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}