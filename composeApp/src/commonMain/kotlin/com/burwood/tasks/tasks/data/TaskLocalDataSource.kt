package com.burwood.tasks.tasks.data

import com.burwood.tasks.tasks.data.database.TaskDao
import com.burwood.tasks.tasks.data.database.TaskEntity.Companion.toTaskEntity
import com.burwood.tasks.tasks.domain.Task
import com.burwood.tasks.tasks.domain.TaskDataSource
import com.burwood.tasks.tasks.domain.TaskList


class TaskLocalDataSource(
    private val taskDao: TaskDao
) : TaskDataSource {

    override suspend fun clear(taskList: TaskList) {
        TODO("Not yet implemented")
    }

    override suspend fun getTask(taskId: String, taskList: TaskList): Task? {
        return taskDao.get(taskId)?.toTask()
    }

    override suspend fun listTasks(taskList: TaskList): List<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun insertTask(task: Task, taskList: TaskList, parent: Task?, previous: Task?): Task {
        taskDao.insert(task.toTaskEntity())
        return task
    }

    override suspend fun moveTask(task: Task, taskList: TaskList, parent: Task?, previous: Task?, destinationTaskList: TaskList?): Task {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(task: Task, taskList: TaskList): Task {
        taskDao.update(task.toTaskEntity())
        return task
    }

    override suspend fun deleteTask(task: Task, taskList: TaskList) {
        taskDao.delete(task.toTaskEntity())
    }

}