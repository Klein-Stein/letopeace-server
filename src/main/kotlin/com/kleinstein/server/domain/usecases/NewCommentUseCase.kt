package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Comment
import com.kleinstein.server.domain.entities.NewComment
import com.kleinstein.server.domain.gateways.IDatabaseGateway
import kotlinx.datetime.Clock
import kotlin.random.Random

class NewCommentUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(postId: Long, newComment: NewComment): Comment {
        return Comment(
            id = Random.nextLong(1, Long.MAX_VALUE),
            postId = postId,
            msg = newComment.msg,
            createdBy = Random.nextLong(1, Long.MAX_VALUE),
            createdAt = Clock.System.now(),
            likesCount = 0,
        )
    }
}
