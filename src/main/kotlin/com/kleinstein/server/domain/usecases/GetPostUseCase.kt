package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Post
import com.kleinstein.server.domain.gateways.IDatabaseGateway

class GetPostUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(postId: Long): Post {
        return this.db.getPost(postId)
    }
}
