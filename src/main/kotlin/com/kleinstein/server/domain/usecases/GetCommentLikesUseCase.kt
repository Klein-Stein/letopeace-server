package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Like
import com.kleinstein.server.domain.entities.Page
import com.kleinstein.server.domain.gateways.IDatabaseGateway

class GetCommentLikesUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(commentId: Long, limit: Int, since: Long?): Page<Like> {
        return this.db.getCommentLikePage(commentId, limit, since)
    }
}
