package com.mocosoft.games.models

import kotlinx.serialization.Serializable

@Serializable
data class IGDBGameList(
    val id: Long,
    val name: String
)

fun IGDBGameList.transform(cover: String?): GameList {
    return GameList(this.id, this.name, cover)
}
