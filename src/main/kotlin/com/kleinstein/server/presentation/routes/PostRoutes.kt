package com.kleinstein.server.presentation.routes

import com.kleinstein.server.domain.entities.NewPost
import com.kleinstein.server.domain.usecases.GetPostsUseCase
import com.kleinstein.server.domain.usecases.NewPostUseCase
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getPostsRoute() {
    get("/posts") {
        val page = call.request.queryParameters["page"]?.toInt() ?: 1
        val size = call.request.queryParameters["size"]?.toInt() ?: 25
        val resultPage = GetPostsUseCase()(page, size)
        call.respond(resultPage)
    }
}

fun Route.postPostRoute() {
    post("/posts") {
        val newPost = call.receive<NewPost>()
        val post = NewPostUseCase()(newPost)
        call.respond(post)
    }
}