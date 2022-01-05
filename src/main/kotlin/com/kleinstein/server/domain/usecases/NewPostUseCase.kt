package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.EGender
import com.kleinstein.server.domain.entities.LiteUser
import com.kleinstein.server.domain.entities.NewPost
import com.kleinstein.server.domain.entities.Post
import kotlinx.datetime.Clock
import kotlin.random.Random

class NewPostUseCase {
    operator fun invoke(newPost: NewPost): Post {
        return Post(
            id = Random.nextLong(1, Long.MAX_VALUE),
            msg = newPost.msg,
            creator = LiteUser(
                id = 1,
                firstName = "Test",
                lastName = "Test",
                nickname = "Test",
                gender = EGender.MALE,
            ),
            createdAt = Clock.System.now(),
            likesCount = 0,
            commentsCount = 0
        )
    }
}