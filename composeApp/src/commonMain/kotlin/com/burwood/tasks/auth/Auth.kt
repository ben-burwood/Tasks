package com.burwood.tasks.auth

import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header


const val AUTH_URL = "https://www.googleapis.com/auth/tasks"


class UnauthorisedGoogleAPIException : Exception("Unauthorised Google API request")


fun HttpClient.configureGoogleAPI(oathToken: String) {
    config {
        defaultRequest {
            header("Authorization", "Bearer $oathToken")
        }
        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, _ ->
                if (exception is Exception) {
                    if (exception is ClientRequestException && exception.response.status.value == 401) throw UnauthorisedGoogleAPIException()
                    throw exception
                }
            }
        }
    }
}