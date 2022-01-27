package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.gateways.IDatabaseGateway

class DeletePostUseCase(db: IDatabaseGateway) : UseCase(db) {
    operator fun invoke(postId: Long) {
        this.db.deletePost(postId)
    }
}
