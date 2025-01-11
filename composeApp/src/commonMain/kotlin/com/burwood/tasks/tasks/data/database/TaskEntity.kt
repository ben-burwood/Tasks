package com.burwood.tasks.tasks.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.burwood.tasks.core.DateTime
import com.burwood.tasks.tasks.domain.Task
import com.burwood.tasks.tasks.domain.TaskStatus


@Entity
data class TaskEntity(
    @PrimaryKey val id: Int,
    val updated: DateTime,
    val title: String,
    val notes: String,
    val position: String,
    val status: TaskStatus,
    val due: DateTime?,
    val completed: DateTime?,
    val deleted: Boolean,
    val hidden: Boolean,
    val parent: Int?,
) {
    fun toTask() = Task(
        id = id,
        updated = updated,
        title = title,
        notes = notes,
        position = position,
        status = status,
        due = due,
        completed = completed,
        deleted = deleted,
        hidden = hidden,
        parent = parent,
    )

    companion object {
        fun Task.toTaskEntity() = TaskEntity(
            id = id,
            updated = updated,
            title = title,
            notes = notes,
            position = position,
            status = status,
            due = due,
            completed = completed,
            deleted = deleted,
            hidden = hidden,
            parent = parent,
        )
    }
}
