package com.kleinstein.server.presentation

import com.kleinstein.server.domain.entities.Post
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.datetime.LocalDateTime

fun Route.getPostsRoute() {
    get("/posts") {
        val post = Post(
            id = 1,
            msg = "Hello, World!",
            createdAt = LocalDateTime(2021, 12, 20, 18, 38),
            createdBy = "Denis Sologub",
            likesCount = 10,
            commentsCount = 100
        )
        call.respond(post)
    }
}