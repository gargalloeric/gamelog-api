package com.mocosoft.games

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val gamesModule = module {
    singleOf(::GameServiceImpl) { bind<GamesService>() }
}