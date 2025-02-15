package com.mocosoft.timetobeat

import com.mocosoft.timetobeat.models.IGDBGameDuration
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody


interface TimeToBeatService {
    suspend fun getTimeToBeat(gameId: Long): IGDBGameDuration
}

class TimeToBeatServiceImpl(private val httpClient: HttpClient) : TimeToBeatService {

    private val endpoint: String = "game_time_to_beats"

    override suspend fun getTimeToBeat(gameId: Long): IGDBGameDuration {

        val response: List<IGDBGameDuration> = httpClient.post(endpoint){
            setBody("fields hastily,normally,completely; where game_id = $gameId;")
        }.body()
        val gameDuration = response.first()

        return gameDuration
    }
}