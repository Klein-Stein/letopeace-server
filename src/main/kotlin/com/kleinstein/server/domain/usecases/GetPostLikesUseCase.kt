package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Like
import com.kleinstein.server.domain.entities.Page
import com.kleinstein.server.domain.gateways.IDatabaseGateway

class GetPostLikesUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(postId: Long, limit: Int, since: Long?): Page<Like> {
        return this.db.getPostLikePage(postId, limit, since)
    }
}
