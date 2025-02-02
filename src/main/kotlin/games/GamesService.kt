package com.mocosoft.games

import com.mocosoft.games.models.GameList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

interface GamesService {
    suspend fun getGames(pageNumber: Long?, pageSize: Long?): List<GameList>
}

class GameServiceImpl(private val httpClient: HttpClient) : GamesService {

    private val endpoint: String = "games"

    override suspend fun getGames(pageNumber: Long?, pageSize: Long?): List<GameList> {
        requireNotNull(pageNumber)
        requireNotNull(pageSize)

        val games: List<GameList> = httpClient.post(endpoint) {
            setBody("fields name;limit $pageSize;offset $pageNumber;sort total_rating desc;where category = (0, 8, 9);")
        }.body()

        return games
    }
}