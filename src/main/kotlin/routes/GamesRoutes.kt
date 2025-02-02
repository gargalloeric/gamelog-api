package com.mocosoft.routes

import com.mocosoft.games.GamesService
import com.mocosoft.games.models.GameList
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.configureGamesRoutes() {

    val service by inject<GamesService>()

    routing {
        get("/games") {
            val pageNumber = call.queryParameters["PageNumber"]?.toLongOrNull()
            val pageSize = call.queryParameters["PageSize"]?.toLongOrNull()

            val games: List<GameList> = service.getGames(pageNumber, pageSize)

            call.respond(games)
        }
    }
}