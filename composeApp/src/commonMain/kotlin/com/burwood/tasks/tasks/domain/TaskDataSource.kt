package com.burwood.tasks.tasks.domain


interface TaskDataSource {

    suspend fun clear(taskList: TaskList)

    suspend fun getTask(taskId: String, taskList: TaskList): Task?

    suspend fun listTasks(taskList: TaskList): List<Task>

    suspend fun insertTask(task: Task, taskList: TaskList, parent: Task?, previous: Task?): Task

    suspend fun moveTask(task: Task, taskList: TaskList, parent: Task?, previous: Task?, destinationTaskList: TaskList?): Task

    suspend fun updateTask(task: Task, taskList: TaskList): Task

    suspend fun deleteTask(task: Task, taskList: TaskList)

}