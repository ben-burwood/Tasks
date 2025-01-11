package com.burwood.tasks.tasks.domain


interface TaskListDataSource {

    suspend fun getTaskList(taskListId: String): TaskList?

    suspend fun listTaskLists(limit: Int): List<TaskList>

    suspend fun insertTaskList(taskList: TaskList): TaskList

    suspend fun updateTaskList(taskList: TaskList): TaskList

    suspend fun deleteTaskList(taskList: TaskList)

}