package com.mocosoft.routes

import com.mocosoft.covers.CoverService
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject

fun Application.configureCoverRoutes() {
    val service by inject<CoverService>()

    routing {
        get("/covers/{gameId}") {
            val gameId = call.parameters["gameId"]?.toLongOrNull()

            val cover: String = service.getCover(gameId)

            call.respond(cover)
        }

        get("/covers") {
            val gameIds = call.queryParameters["gameIds"]

            val gameIdsLong = gameIds?.split(",")?.mapNotNull { it.toLongOrNull() }

            val covers = service.getCovers(gameIdsLong)

            call.respond(covers)
        }
    }
}