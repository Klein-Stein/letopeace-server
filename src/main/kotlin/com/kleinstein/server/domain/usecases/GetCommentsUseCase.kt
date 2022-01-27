package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Comment
import com.kleinstein.server.domain.entities.Page

class GetCommentsUseCase {
    operator fun invoke(postId: Long, page: Int, size: Int): Page<Comment> {
        return Page(
            page = page,
            total = Long.MAX_VALUE,
            data = arrayListOf()
        )
    }
}