package com.kleinstein.server.domain.entities

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Like(val id: Long, val createdBy: Long, val createdAt: Instant)
