package com.mocosoft.covers

import com.mocosoft.covers.models.IGDBCover
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

interface CoverService {
    suspend fun getCover(gameId: Long?): String
}

class CoverServiceImpl(private val httpClient: HttpClient) : CoverService {

    private val endpoint = "covers"

    override suspend fun getCover(gameId: Long?): String {
        requireNotNull(gameId)

        val response: List<IGDBCover> = httpClient.post(endpoint){
            setBody("fields url; where game = $gameId;")
        }.body()
        val normal = response.firstOrNull()?.url?.replace("t_thumb","t_original") ?: ""

        return normal

    }
}