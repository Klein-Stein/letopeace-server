package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Comment
import com.kleinstein.server.domain.entities.Page

class GetCommentsUseCase {
    operator fun invoke(postId: Long, limit: Int, since: Long?): Page<Comment> {
        return Page(
            data = arrayListOf(),
            next = null
        )
    }
}