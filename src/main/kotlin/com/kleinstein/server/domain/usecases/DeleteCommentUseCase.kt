package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.gateways.IDatabaseGateway

class DeleteCommentUseCase(db: IDatabaseGateway) : UseCase(db) {
    operator fun invoke(commentId: Long) {
    }
}
