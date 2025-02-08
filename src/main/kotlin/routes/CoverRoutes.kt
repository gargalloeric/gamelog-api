package com.mocosoft.routes

import com.mocosoft.covers.CoverService
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.get
import io.ktor.server.util.getOrFail
import org.koin.ktor.ext.inject

fun Application.configureCoverRoutes() {
    val service by inject<CoverService>()

    routing {
        get("/covers/{gameId}") {
            val gameId = call.pathParameters.getOrFail("gameId").toLong()

            val cover: String = service.getCover(gameId)

            call.respond(cover)
        }

        get("/covers") {
            val gameIds = call.queryParameters.getOrFail("gameIds")

            val gameIdsLong = gameIds.split(",").map { it.toLong() }

            val covers = service.getCovers(gameIdsLong)

            call.respond(covers)
        }
    }
}