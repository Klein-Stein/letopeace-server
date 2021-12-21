package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Comment
import com.kleinstein.server.domain.entities.Page
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random

class GetCommentsUseCase {
    operator fun invoke(postId: Long, page: Int, size: Int): Page<Comment> {
        val authors = arrayListOf("Arthur", "Bob", "David", "Harry")
        return Page(
            page = page,
            total = Long.MAX_VALUE,
            items = List(size) {
                val currentMoment = Clock.System.now()
                Comment(
                    id = Random.nextLong(1, Long.MAX_VALUE),
                    parentId = null,
                    postId = postId,
                    msg = "Hello, World ${it + 1}!",
                    createdAt = currentMoment.toLocalDateTime(TimeZone.UTC),
                    createdBy = authors[Random.nextInt(0, 4)],
                    likesCount = Random.nextInt(0, 1000),
                    commentsCount = Random.nextInt(0, 1000)
                )
            }
        )
    }
}