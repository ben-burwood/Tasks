package com.burwood.tasks.tasks.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.burwood.tasks.auth.UnauthorisedGoogleAPIException
import com.burwood.tasks.auth.configureGoogleAPI
import com.burwood.tasks.core.data.DataStoreItem.Companion.getOathToken
import com.burwood.tasks.core.data.constructGoogleTasksApiUrl
import com.burwood.tasks.tasks.data.dto.TaskDto
import com.burwood.tasks.tasks.data.dto.toTask
import com.burwood.tasks.tasks.domain.Task
import com.burwood.tasks.tasks.domain.TaskDataSource
import com.burwood.tasks.tasks.domain.TaskList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray


class TaskRemoteDataSource(
    private val client: HttpClient,
    private val datastore: DataStore<Preferences>
) : TaskDataSource {

    init {
        val accessToken = runBlocking { datastore.getOathToken() } ?: throw UnauthorisedGoogleAPIException()
        client.configureGoogleAPI(accessToken)
    }

    override suspend fun clear(taskList: TaskList) {
        val url = constructGoogleTasksApiUrl("/lists/${taskList.id}/tasks/clear")

        client.post(url)
    }

    override suspend fun getTask(taskId: String, taskList: TaskList): Task {
        val url = constructGoogleTasksApiUrl("/lists/${taskList.id}/tasks/${taskId}")

        return client.get(url).body<TaskDto>().toTask()
    }

    override suspend fun listTasks(taskList: TaskList): List<Task> {
        val url = constructGoogleTasksApiUrl("/lists/${taskList.id}/tasks")

        return client.get(url).body<JsonObject>()["items"]?.jsonArray?.map {
            Json.decodeFromJsonElement<TaskDto>(it).toTask()
        } ?: emptyList()
    }

    override suspend fun insertTask(task: Task, taskList: TaskList, parent: Task?, previous: Task?): Task {
        val url = constructGoogleTasksApiUrl("/lists/${taskList.id}/tasks")

        return client.post(url) {
            if (parent != null) parameter("parent", parent.id)
            if (previous != null) parameter("previous", previous.id)
            setBody(task)
        }.body<TaskDto>().toTask()
    }

    override suspend fun moveTask(task: Task, taskList: TaskList, parent: Task?, previous: Task?, destinationTaskList: TaskList?): Task {
        val url = constructGoogleTasksApiUrl("/lists/${taskList.id}/tasks/${task.id}/move")

        return client.post(url) {
            if (parent != null) parameter("parent", parent.id)
            if (previous != null) parameter("previous", previous.id)
            if (destinationTaskList != null) parameter("destinationTasklist", destinationTaskList.id)
        }.body<TaskDto>().toTask()
    }

    override suspend fun updateTask(task: Task, taskList: TaskList): Task {
        val url = constructGoogleTasksApiUrl("/lists/${taskList.id}/tasks/${task.id}")

        return client.post(url) {
            setBody(task)
        }.body<TaskDto>().toTask()
    }

    override suspend fun deleteTask(task: Task, taskList: TaskList) {
        val url = constructGoogleTasksApiUrl("/lists/${taskList.id}/tasks/${task.id}")

        client.delete(url)
    }

}