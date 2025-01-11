package com.burwood.tasks.tasks.domain

import com.burwood.tasks.core.DateTime


data class TaskList(
    val id: String,
    val title: String,
    val updated: DateTime,
)
