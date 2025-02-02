package com.mocosoft.http

import com.mocosoft.http.models.TokenInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.forms.submitForm
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.appendIfNameAbsent
import org.koin.dsl.module

var tokenClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json()
    }
}

suspend fun getAccessToken(): BearerTokens {
    val tokenInfo: TokenInfo = tokenClient.submitForm(
        url = "https://id.twitch.tv/oauth2/token",
        formParameters = parameters {
            append("client_id", System.getenv("CLIENT_ID"))
            append("client_secret", System.getenv("CLIENT_SECRET"))
            append("grant_type", "client_credentials")
        }
    ).body()
    return BearerTokens(accessToken = tokenInfo.accessToken, refreshToken = null)
}

val httpClientModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }

            install(Auth) {
                bearer {
                    loadTokens { getAccessToken() }

                    refreshTokens { getAccessToken() }
                }
            }

            defaultRequest {
                url("https://api.igdb.com/v4/")

                headers.appendIfNameAbsent("Client-ID", System.getenv("CLIENT_ID"))
                headers.appendIfNameAbsent("Content-Type", "text/plain")
            }
        }
    }
}