package com.burwood.tasks.tasks.data

import com.burwood.tasks.tasks.data.database.TaskListDao
import com.burwood.tasks.tasks.data.database.TaskListEntity.Companion.toTaskListEntity
import com.burwood.tasks.tasks.domain.TaskList
import com.burwood.tasks.tasks.domain.TaskListDataSource


class TaskListLocalDataSource(
    private val taskListDao: TaskListDao
) : TaskListDataSource {

    override suspend fun getTaskList(taskListId: String): TaskList? {
        return taskListDao.get(taskListId)?.toTaskList()
    }

    override suspend fun listTaskLists(limit: Int): List<TaskList> {
        TODO("Not yet implemented")
    }

    override suspend fun insertTaskList(taskList: TaskList): TaskList {
        taskListDao.insert(taskList.toTaskListEntity())
        return taskList
    }

    override suspend fun updateTaskList(taskList: TaskList): TaskList {
        taskListDao.update(taskList.toTaskListEntity())
        return taskList
    }

    override suspend fun deleteTaskList(taskList: TaskList) {
        taskListDao.delete(taskList.toTaskListEntity())
    }

}