package com.mocosoft.games.models

import kotlinx.serialization.Serializable

@Serializable
data class GameDetails(
    val name: String,
    val rating: Double,
    val summary: String,
    val cover: String,
    val durationFast: Long?,
    val durationNormal: Long?,
    val durationComplete: Long?
)