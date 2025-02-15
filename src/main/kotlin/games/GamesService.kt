package com.mocosoft.games

import com.mocosoft.covers.CoverService
import com.mocosoft.games.models.GameDetails
import com.mocosoft.covers.models.IGDBCover
import com.mocosoft.games.models.GameList
import com.mocosoft.games.models.IGDBGameDetails
import com.mocosoft.games.models.IGDBGameList
import com.mocosoft.timetobeat.TimeToBeatService
import com.mocosoft.games.models.transform
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Parameters
import io.ktor.server.util.getOrFail

interface GamesService {
    suspend fun getGames(queryParameters: Parameters): List<GameList>
    suspend fun getGameDetails(pathParameters: Parameters): GameDetails?
}

class GameServiceImpl(
    private val httpClient: HttpClient,
    private val coverService: CoverService,
    private val timeToBeatService: TimeToBeatService
) : GamesService {

    private val endpoint: String = "games"

    override suspend fun getGames(queryParameters: Parameters): List<GameList> {
        val pageSize = queryParameters.getOrFail("pageSize")
        val pageNumber = queryParameters.getOrFail("pageNumber")

        val games: List<IGDBGameList> = httpClient.post(endpoint) {
            setBody("fields name;limit $pageSize;offset $pageNumber;sort total_rating desc;where category = (0, 8, 9);")
        }.body()

        val gameIds: List<Long> = games.map { it.id }

        val covers: List<IGDBCover> = coverService.getCovers(gameIds)

        val results: List<GameList> = buildList {
            for (game in games) {
                val cover = covers.find { it.gameId == game.id }
                add(game.transform(cover?.url))
            }
        }

        return results
    }

    override suspend fun getGameDetails(pathParameters: Parameters): GameDetails? {
        val gameId = pathParameters.getOrFail("gameId").toLong()
        var gameDetails: GameDetails? = null

        val igdbDetails: IGDBGameDetails? = httpClient.post(endpoint) {
            setBody("fields name, total_rating, summary; where id = $gameId;")
        }.body<List<IGDBGameDetails>>().firstOrNull()

        if (igdbDetails != null) {
            val cover = coverService.getCover(gameId)

            val duration = timeToBeatService.getTimeToBeat(gameId)

            gameDetails = GameDetails(
                name = igdbDetails.name,
                summary = igdbDetails.summary,
                rating = igdbDetails.rating,
                cover = cover,
                durationFast = duration.hastily,
                durationNormal = duration.normally,
                durationComplete = duration.completely
            )
        }

        return gameDetails
    }
}