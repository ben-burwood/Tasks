package com.burwood.tasks.tasks.domain

import com.burwood.tasks.core.DateTime


data class Task(
    val id: Int,
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
    fun isComplete(): Boolean {
        return completed != null
    }
}
