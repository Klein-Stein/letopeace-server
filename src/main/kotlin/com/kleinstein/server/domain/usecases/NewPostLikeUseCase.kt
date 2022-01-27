package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.*
import com.kleinstein.server.domain.gateways.IDatabaseGateway

class NewPostLikeUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(postId: Long, createdBy: Long): Like {
        return this.db.addPostLike(postId, createdBy)
    }
}
