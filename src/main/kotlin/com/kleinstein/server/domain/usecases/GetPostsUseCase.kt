package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Page
import com.kleinstein.server.domain.entities.Post

class GetPostsUseCase {
    operator fun invoke(limit: Int, since: Long?): Page<Post> {
        return Page(
            data = arrayListOf(),
            next = null
        )
    }
}
