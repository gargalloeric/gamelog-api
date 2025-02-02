package com.mocosoft.plugins

import com.mocosoft.covers.coverModule
import com.mocosoft.games.gamesModule
import com.mocosoft.http.httpClientModule
import com.mocosoft.timetobeat.timeToBeatModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(gamesModule)
        modules(httpClientModule)
        modules(coverModule)
        modules(timeToBeatModule)
    }
}