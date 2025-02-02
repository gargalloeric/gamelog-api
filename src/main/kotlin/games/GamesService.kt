package com.mocosoft.games

import com.mocosoft.games.models.IGDBGameList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

interface GamesService {
    suspend fun getGames(pageNumber: Long?, pageSize: Long?): List<IGDBGameList>
}

class GameServiceImpl(private val httpClient: HttpClient) : GamesService {

    private val endpoint: String = "games"

    override suspend fun getGames(pageNumber: Long?, pageSize: Long?): List<IGDBGameList> {
        requireNotNull(pageNumber)
        requireNotNull(pageSize)

        val games: List<IGDBGameList> = httpClient.post(endpoint) {
            setBody("fields name;limit $pageSize;offset $pageNumber;sort total_rating desc;where category = (0, 8, 9);")
        }.body()

        return games
    }
}