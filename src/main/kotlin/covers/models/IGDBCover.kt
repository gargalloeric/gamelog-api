package com.mocosoft.covers.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IGDBCover(
    val id: Long,
    @SerialName("game")
    val gameId: Long,
    var url: String
)