package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Page
import com.kleinstein.server.domain.entities.Post

class GetPostsUseCase {
    operator fun invoke(page: Int, size: Int): Page<Post> {
        return Page(
            page = page,
            total = Long.MAX_VALUE,
            data = arrayListOf()
        )
    }
}