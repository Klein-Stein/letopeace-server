package com.kleinstein.server.domain.entities

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class NewPost(val msg: String, val createdBy: String)

@Serializable
data class Post(val id: Long, val msg: String, val createdAt: LocalDateTime, val createdBy: String,
                val likesCount: Long, val commentsCount: Long)
