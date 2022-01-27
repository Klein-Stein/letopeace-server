package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Comment
import com.kleinstein.server.domain.entities.NewComment
import com.kleinstein.server.domain.gateways.IDatabaseGateway

class NewCommentUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(postId: Long, newComment: NewComment, createdBy: Long): Comment {
        return this.db.addComment(postId, newComment, createdBy)
    }
}
