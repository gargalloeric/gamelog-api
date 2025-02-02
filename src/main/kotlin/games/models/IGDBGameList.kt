package com.mocosoft.games.models

import kotlinx.serialization.Serializable

@Serializable
data class IGDBGameList(
    val id: Long,
    val name: String
)
