package com.kleinstein.server.domain.entities

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Long,
    val msg: String,
    val creator: LiteUser,
    val likesCount: Int,
    val commentsCount: Int,
    val createdAt: Instant,
)
