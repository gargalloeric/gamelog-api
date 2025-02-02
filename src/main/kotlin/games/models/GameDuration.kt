package com.mocosoft.games.models

import kotlinx.serialization.Serializable

@Serializable
data class GameDuration(
    val hastily: Int,
    val normally: Int,
    val completely: Int
)