package com.mocosoft.plugins

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.append
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val httpClientModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }

            install(Auth) {
                bearer {

                }
            }

            defaultRequest {
                url("https://api.igdb.com/v4")
            }
        }
    }
}

fun getOAuthAccessToken() {
    // TODO: Retrieve data from .env file or something like that
    val authorizationUrlQuery = parameters {
        append("client_id", "")
        append("client_secret", "")
        append("grant_type", "")
    }
}