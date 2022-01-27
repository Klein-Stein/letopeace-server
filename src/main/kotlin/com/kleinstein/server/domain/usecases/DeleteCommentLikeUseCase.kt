package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.gateways.IDatabaseGateway

class DeleteCommentLikeUseCase(db: IDatabaseGateway) : UseCase(db) {
    operator fun invoke(likeId: Long) {
    }
}
