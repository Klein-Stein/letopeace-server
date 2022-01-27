package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.NewPost
import com.kleinstein.server.domain.entities.Post
import com.kleinstein.server.domain.gateways.IDatabaseGateway

class NewPostUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(newPost: NewPost, createdBy: Long): Post {
        return this.db.addPost(newPost, createdBy)
    }
}
