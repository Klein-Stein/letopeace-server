package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Page
import com.kleinstein.server.domain.entities.Post
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random

class GetPostsUseCase {
    operator fun invoke(page: Int, size: Int): Page<Post> {
        val authors = arrayListOf("Arthur", "Bob", "David", "Harry")
        return Page(
            page = page,
            total = Long.MAX_VALUE,
            items = List(size) {
                val currentMoment = Clock.System.now()
                Post(
                    id = Random.nextLong(1, Long.MAX_VALUE),
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