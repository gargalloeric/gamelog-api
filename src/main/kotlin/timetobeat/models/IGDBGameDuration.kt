package com.mocosoft.timetobeat.models

import kotlinx.serialization.Serializable

@Serializable
data class IGDBGameDuration(
    val id: Long,
    val hastily: Long?,
    val normally: Long?,
    val completely: Long?
)