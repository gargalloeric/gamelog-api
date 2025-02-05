package com.mocosoft.games

import com.mocosoft.games.models.IGDBGameDetails
import com.mocosoft.games.models.IGDBGameList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Parameters
import io.ktor.server.util.getOrFail

interface GamesService {
    suspend fun getGames(queryParameters: Parameters): List<IGDBGameList>
    suspend fun getGameDetails(queryParameters: Parameters): IGDBGameDetails
}

class GameServiceImpl(private val httpClient: HttpClient) : GamesService {

    private val endpoint: String = "games"

    override suspend fun getGames(queryParameters: Parameters): List<IGDBGameList> {
        val pageSize = queryParameters.getOrFail("pageSize")
        val pageNumber = queryParameters.getOrFail("pageNumber")

        val games: List<IGDBGameList> = httpClient.post(endpoint) {
            setBody("fields name;limit $pageSize;offset $pageNumber;sort total_rating desc;where category = (0, 8, 9);")
        }.body()

        return games
    }

    override suspend fun getGameDetails(queryParameters: Parameters): IGDBGameDetails {
        val gameId = queryParameters.getOrFail("gameId")
        val gameDetails: List<IGDBGameDetails> = httpClient.post(endpoint) {
            setBody("fields name, total_rating, summary; where id = $gameId;")
        }.body()

        return gameDetails.first()
    }
}