package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Comment
import com.kleinstein.server.domain.entities.NewComment
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random

class NewCommentUseCase {
    operator fun invoke(postId: Long, newComment: NewComment): Comment {
        val currentMoment = Clock.System.now()
        return Comment(
            id = Random.nextLong(1, Long.MAX_VALUE),
            parentId = newComment.parentId,
            postId = postId,
            msg = newComment.msg,
            createdBy = newComment.createdBy,
            createdAt = currentMoment.toLocalDateTime(TimeZone.UTC),
            likesCount = 0,
            commentsCount = 0
        )
    }
}