package com.mocosoft.games

import com.mocosoft.games.models.GameDuration
import io.ktor.client.HttpClient

interface GameService {
    suspend fun getTimeToBeat(gameId: Int): GameDuration
}

class GameServiceImpl(val httpClient: HttpClient) : GameService {


    override suspend fun getTimeToBeat(gameId: Int): GameDuration {
        val requestBody = "fields hastily,normally,completely;\n" +
                "where game_id = $gameId;"

        try {
            val response: GameDuration = httpClient.post(){
                body = requestBody
            }

            return response
        }catch (e: Exception){
            println(e.message)
        }
    }
}