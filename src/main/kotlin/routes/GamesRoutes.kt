package com.mocosoft.routes

import com.mocosoft.games.GamesService
import com.mocosoft.games.models.GameList
import com.mocosoft.games.models.GameDetails
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.configureGamesRoutes() {

    val service by inject<GamesService>()

    routing {
        get("/games") {

            val games: List<GameList> = service.getGames(call.queryParameters)

            call.respond(games)
        }
        get("/games/{gameId}") {

            val gameDetails: GameDetails? = service.getGameDetails(call.pathParameters)

            if (gameDetails != null) {
                call.respond(gameDetails)
            } else {
                call.response.status(HttpStatusCode.NotFound)
            }
        }
    }
}