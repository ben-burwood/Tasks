package com.burwood.tasks.tasks.data.dto

import com.burwood.tasks.tasks.domain.Task
import com.burwood.tasks.tasks.domain.TaskStatus
import kotlinx.serialization.Serializable


@Serializable
data class TaskDto(
    val id: Int,
    val updated: String,
    val title: String,
    val notes: String,
    val position: String,
    val status: String,
    val due: String?,
    val completed: String?,
    val deleted: Boolean,
    val hidden: Boolean,
    val parent: Int?,
)


fun TaskDto.toTask() = Task(
    id = id,
    updated = updated,
    title = title,
    notes = notes,
    position = position,
    status = TaskStatus.valueOf(status),
    due = due,
    completed = completed,
    deleted = deleted,
    hidden = hidden,
    parent = parent,
)



