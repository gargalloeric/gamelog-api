package com.mocosoft.routes

import com.mocosoft.timetobeat.TimeToBeatService
import com.mocosoft.timetobeat.models.IGDBGameDuration
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.configureTimeToBeatRoutes() {

    val service by inject<TimeToBeatService>()

    routing {
        get("/timetobeat") {
            val gameId = call.queryParameters["GameID"]?.toLongOrNull()

            val timeToBeat: IGDBGameDuration? = service.getTimeToBeat(gameId)

            if (timeToBeat != null) {
                call.respond(timeToBeat)
            }else{
                call.response.status(HttpStatusCode.NotFound)
            }
        }
    }
}