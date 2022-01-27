package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Like
import com.kleinstein.server.domain.entities.Page

class GetPostLikesUseCase {
    operator fun invoke(postId: Long, limit: Int, since: Long?): Page<Like> {
        return Page(
            data = arrayListOf(),
            next = null
        )
    }
}
