package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.gateways.IDatabaseGateway

class DeletePostLikeUseCase(db: IDatabaseGateway) : UseCase(db) {
    operator fun invoke(likeId: Long) {
        this.db.deletePostLike(likeId)
    }
}
