package com.kleinstein.server.domain.entities

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val id: Long,
    val postId: Long,
    val msg: String,
    val creator: LiteUser,
    val likesCount: Int,
    val createdAt: Instant,
)
