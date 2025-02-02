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
            val pageNumber = call.queryParameters["PageNumber"]
            val pageSize = call.queryParameters["PageSize"]

            requireNotNull(pageNumber)
            requireNotNull(pageSize)

            val games: List<GameList> = service.getGames(pageNumber.toLong(), pageSize.toLong())

            call.respond(games)
        }
    }
}