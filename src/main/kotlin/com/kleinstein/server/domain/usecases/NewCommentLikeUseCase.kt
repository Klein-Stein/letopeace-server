package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.*
import com.kleinstein.server.domain.gateways.IDatabaseGateway

class NewCommentLikeUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(commentId: Long, createdBy: Long): Like {
        return this.db.addCommentLike(commentId, createdBy)
    }
}
