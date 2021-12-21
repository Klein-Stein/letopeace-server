package com.kleinstein.server.domain.entities

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Comment(val id: Long, val parentId: Long?, val postId: Long, val msg: String, val createdAt: LocalDateTime,
                   val createdBy: String, val likesCount: Int, val commentsCount: Int)
