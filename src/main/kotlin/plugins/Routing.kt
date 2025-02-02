package com.mocosoft.plugins

import com.mocosoft.routes.configureCoverRoutes
import com.mocosoft.routes.configureGamesRoutes
import io.ktor.server.application.Application
import io.ktor.server.http.content.staticResources
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        configureGamesRoutes()
        configureCoverRoutes()
        staticResources("/static", "static")
    }
}