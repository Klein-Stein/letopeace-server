package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Post
import com.kleinstein.server.domain.gateways.IDatabaseGateway
import kotlinx.datetime.Clock

class GetPostUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(postId: Long): Post {
        return Post(
            id = postId,
            msg = "",
            createdBy = 1,
            likesCount = 0,
            commentsCount = 0,
            createdAt = Clock.System.now()
        )
    }
}
