package com.mocosoft.games

import com.mocosoft.covers.CoverService
import com.mocosoft.games.models.GameDetails
import com.mocosoft.games.models.IGDBGameDetails
import com.mocosoft.games.models.IGDBGameList
import com.mocosoft.timetobeat.TimeToBeatService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Parameters
import io.ktor.server.util.getOrFail

interface GamesService {
    suspend fun getGames(queryParameters: Parameters): List<IGDBGameList>
    suspend fun getGameDetails(pathParameters: Parameters): GameDetails
}

class GameServiceImpl(private val httpClient: HttpClient, private val coverService: CoverService, private val timeToBeatService: TimeToBeatService) : GamesService {

    private val endpoint: String = "games"

    override suspend fun getGames(queryParameters: Parameters): List<IGDBGameList> {
        val pageSize = queryParameters.getOrFail("pageSize")
        val pageNumber = queryParameters.getOrFail("pageNumber")

        val games: List<IGDBGameList> = httpClient.post(endpoint) {
            setBody("fields name;limit $pageSize;offset $pageNumber;sort total_rating desc;where category = (0, 8, 9);")
        }.body()

        return games
    }

    override suspend fun getGameDetails(pathParameters: Parameters): GameDetails {
        val gameId = pathParameters.getOrFail("gameId")
        val gameIdLong = gameId.toLong()

        val igdbDetails: IGDBGameDetails = httpClient.post(endpoint) {
            setBody("fields name, total_rating, summary; where id = $gameId;")
        }.body<List<IGDBGameDetails>>().first()

        val cover = coverService.getCover(gameIdLong)

        val duration = timeToBeatService.getTimeToBeat(gameIdLong)

        val gameDetails = GameDetails(
            name = igdbDetails.name,
            summary = igdbDetails.summary,
            rating = igdbDetails.rating,
            cover = cover,
            durationFast = duration?.hastily,
            durationNormal = duration?.normally,
            durationComplete = duration?.completely
        )

        return gameDetails
    }
}