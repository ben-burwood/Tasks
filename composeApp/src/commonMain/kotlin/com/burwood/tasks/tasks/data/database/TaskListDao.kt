package com.burwood.tasks.tasks.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TaskListDao {

    @Query("SELECT * FROM TaskListEntity WHERE id = :taskListId")
    suspend fun get(taskListId: String): TaskListEntity?

    @Insert
    suspend fun insert(taskList: TaskListEntity)

    @Update
    suspend fun update(taskList: TaskListEntity)

    @Delete
    suspend fun delete(taskList: TaskListEntity)

}