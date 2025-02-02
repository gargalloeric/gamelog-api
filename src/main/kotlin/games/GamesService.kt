package com.mocosoft.games

import com.mocosoft.games.models.GameList

interface GamesService {
    suspend fun getGames(pageNumber: Long?, pageSize: Long?): List<GameList>
}

class GameServiceImpl : GamesService {
    override suspend fun getGames(pageNumber: Long?, pageSize: Long?): List<GameList> {
        requireNotNull(pageNumber)
        requireNotNull(pageSize)

        val list = listOf(
            GameList(id = 1, name = "Test", cover = 2)
        )

        return list
    }
}