package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.NewPost
import com.kleinstein.server.domain.entities.Post
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random

class NewPostUseCase {
    operator fun invoke(newPost: NewPost): Post {
        val currentMoment = Clock.System.now()
        return Post(
            id = Random.nextLong(1, Long.MAX_VALUE),
            msg = newPost.msg,
            createdBy = newPost.createdBy,
            createdAt = currentMoment.toLocalDateTime(TimeZone.UTC),
            likesCount = 0,
            commentsCount = 0
        )
    }
}