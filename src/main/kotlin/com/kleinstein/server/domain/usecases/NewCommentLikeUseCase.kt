package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.*
import com.kleinstein.server.domain.gateways.IDatabaseGateway
import kotlinx.datetime.Clock
import kotlin.random.Random

class NewCommentLikeUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(commentId: Long, createdBy: Long): Like {
        return Like(
            id = Random.nextLong(1, Long.MAX_VALUE),
            createdBy = createdBy,
            createdAt = Clock.System.now(),
        )
    }
}
