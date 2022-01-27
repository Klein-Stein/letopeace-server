package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.NewPost
import com.kleinstein.server.domain.entities.Post
import com.kleinstein.server.domain.gateways.IDatabaseGateway
import kotlinx.datetime.Clock
import kotlin.random.Random

class NewPostUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(newPost: NewPost): Post {
        return Post(
            id = Random.nextLong(1, Long.MAX_VALUE),
            msg = newPost.msg,
            createdBy = Random.nextLong(1, Long.MAX_VALUE),
            createdAt = Clock.System.now(),
            likesCount = 0,
            commentsCount = 0
        )
    }
}
