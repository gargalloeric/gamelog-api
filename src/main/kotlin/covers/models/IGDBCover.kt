package com.mocosoft.covers.models

import kotlinx.serialization.Serializable

@Serializable
data class IGDBCover(
    val id: Int,
    val url: String
)