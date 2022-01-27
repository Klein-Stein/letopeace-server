package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Page
import com.kleinstein.server.domain.entities.Post
import com.kleinstein.server.domain.gateways.IDatabaseGateway

class GetPostsUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(limit: Int, since: Long?): Page<Post> {
        return this.db.getPostPage(limit, since)
    }
}
