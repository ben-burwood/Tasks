package com.burwood.tasks.tasks.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.burwood.tasks.core.DateTime
import com.burwood.tasks.tasks.domain.TaskList


@Entity
data class TaskListEntity(
    @PrimaryKey val id: String,
    val title: String,
    val updated: DateTime,
) {
    fun toTaskList() = TaskList(
        id = id,
        title = title,
        updated = updated,
    )

    companion object {
        fun TaskList.toTaskListEntity() = TaskListEntity(
            id = id,
            title = title,
            updated = updated,
        )
    }
}
