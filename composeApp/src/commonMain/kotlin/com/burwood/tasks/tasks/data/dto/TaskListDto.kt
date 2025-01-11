package com.burwood.tasks.tasks.data.dto

import com.burwood.tasks.tasks.domain.TaskList
import kotlinx.serialization.Serializable


@Serializable
data class TaskListDto(
    val id: String,
    val title: String,
    val updated: String,
)


fun TaskListDto.toTaskList() = TaskList(
    id = id,
    title = title,
    updated = updated,
)