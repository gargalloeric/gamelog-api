package com.mocosoft.games.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IGDBGameDetails(
    val id: Long,
    val name: String,
    @SerialName("total_rating")
    val rating: Double = 0.0,
    val summary: String
)
