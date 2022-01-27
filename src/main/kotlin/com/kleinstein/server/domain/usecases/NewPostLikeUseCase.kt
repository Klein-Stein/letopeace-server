package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.*
import com.kleinstein.server.domain.gateways.IDatabaseGateway
import kotlinx.datetime.Clock
import kotlin.random.Random

class NewPostLikeUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(postId: Long, createdBy: Long): Like {
        return Like(
            id = Random.nextLong(1, Long.MAX_VALUE),
            createdBy = createdBy,
            createdAt = Clock.System.now(),
        )
    }
}
