package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.*
import kotlinx.datetime.Clock
import kotlin.random.Random

class NewCommentLikeUseCase {
    operator fun invoke(commentId: Long, createdBy: Long): Like {
        return Like(
            id = Random.nextLong(1, Long.MAX_VALUE),
            createdBy = createdBy,
            createdAt = Clock.System.now(),
        )
    }
}
