package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Like
import com.kleinstein.server.domain.entities.Page

class GetCommentLikesUseCase {
    operator fun invoke(commentId: Long, limit: Int, since: Long?): Page<Like> {
        return Page(
            data = arrayListOf(),
            next = null
        )
    }
}
