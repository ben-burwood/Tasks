package com.burwood.tasks.core.data


private const val SERVICE_ENDPOINT = "https://tasks.googleapis.com"

private const val BASE_URL = "$SERVICE_ENDPOINT/tasks/v1/"


internal fun constructGoogleTasksApiUrl(url: String): String {
    return when {
        url.contains(BASE_URL) -> url
        url.startsWith("/") -> BASE_URL + url.drop(1)
        else -> BASE_URL + url
    }
}