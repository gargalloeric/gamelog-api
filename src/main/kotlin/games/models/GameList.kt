package com.mocosoft.games.models

import kotlinx.serialization.Serializable

@Serializable
data class GameList(
    val id: Long,
    val name: String
)
