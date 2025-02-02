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
        get("/covers") {
            val gameId = call.queryParameters["GameID"]?.toLongOrNull()

            val cover: String = service.getCover(gameId)

            call.respond(cover)
        }
    }
}