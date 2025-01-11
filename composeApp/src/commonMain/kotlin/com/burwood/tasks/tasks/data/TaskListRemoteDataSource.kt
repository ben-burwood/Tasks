package com.burwood.tasks.tasks.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.burwood.tasks.auth.UnauthorisedGoogleAPIException
import com.burwood.tasks.auth.configureGoogleAPI
import com.burwood.tasks.core.data.DataStoreItem.Companion.getOathToken
import com.burwood.tasks.core.data.constructGoogleTasksApiUrl
import com.burwood.tasks.tasks.data.dto.TaskListDto
import com.burwood.tasks.tasks.data.dto.toTaskList
import com.burwood.tasks.tasks.domain.TaskList
import com.burwood.tasks.tasks.domain.TaskListDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray


class TaskListRemoteDataSource(
    private val client: HttpClient,
    private val datastore: DataStore<Preferences>
) : TaskListDataSource {

    override suspend fun getTaskList(taskListId: String): TaskList {
        val url = constructGoogleTasksApiUrl("/users/@me/lists/${taskListId}")

        return client.get(url).body<TaskListDto>().toTaskList()
    }

    override suspend fun listTaskLists(limit: Int): List<TaskList> {
        if (limit < 0 || limit > 100) {
            throw IllegalArgumentException("Limit must be between 0 and 100")
        }

        val url = constructGoogleTasksApiUrl("/users/@me/lists")

        return client.get(url).body<JsonObject>()["items"]?.jsonArray?.map {
            Json.decodeFromJsonElement<TaskListDto>(it).toTaskList()
        } ?: emptyList()
    }

    override suspend fun insertTaskList(taskList: TaskList): TaskList {
        val url = constructGoogleTasksApiUrl("/users/@me/lists")

        return client.post(url) {
            setBody(taskList)
        }.body<TaskListDto>().toTaskList()
    }

    override suspend fun updateTaskList(taskList: TaskList): TaskList {
        val url = constructGoogleTasksApiUrl("/users/@me/lists/${taskList.id}")

        return client.put(url) {
            setBody(taskList)
        }.body<TaskListDto>().toTaskList()
    }

    override suspend fun deleteTaskList(taskList: TaskList) {
        val url = constructGoogleTasksApiUrl("/users/@me/lists/${taskList.id}")

        client.delete(url)
    }

}