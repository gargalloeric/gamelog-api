package com.mocosoft.timetobeat.models

import kotlinx.serialization.Serializable

@Serializable
data class IGDBGameDuration(
    val id: Long,
    val hastily: Long? = 0,
    val normally: Long? = 0,
    val completely: Long? = 0
)