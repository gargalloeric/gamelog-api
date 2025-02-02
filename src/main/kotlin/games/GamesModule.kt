package com.mocosoft.games

import org.koin.dsl.module

val gamesModule = module {
    single<GamesService> { GameServiceImpl(get()) }
}