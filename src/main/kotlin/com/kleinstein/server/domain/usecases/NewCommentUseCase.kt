package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Comment
import com.kleinstein.server.domain.entities.EGender
import com.kleinstein.server.domain.entities.LiteUser
import com.kleinstein.server.domain.entities.NewComment
import kotlinx.datetime.Clock
import kotlin.random.Random

class NewCommentUseCase {
    operator fun invoke(postId: Long, newComment: NewComment): Comment {
        return Comment(
            id = Random.nextLong(1, Long.MAX_VALUE),
            postId = postId,
            msg = newComment.msg,
            creator = LiteUser(
                id = 1,
                firstName = "Test",
                lastName = "Test",
                nickname = "Test",
                gender = EGender.MALE,
            ),
            createdAt = Clock.System.now(),
            likesCount = 0,
        )
    }
}