package com.mocosoft.covers.models

import kotlinx.serialization.Serializable

@Serializable
data class CoverResponse(
    val id: Int,
    val url: String
)