package com.kleinstein.server.presentation.routes

import com.kleinstein.server.domain.entities.NewPost
import com.kleinstein.server.domain.usecases.DeletePostUseCase
import com.kleinstein.server.domain.usecases.GetPostUseCase
import com.kleinstein.server.domain.usecases.GetPostsUseCase
import com.kleinstein.server.domain.usecases.NewPostUseCase
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.deletePostRoute() {
    delete("/posts/{postId}") {
        val postId = call.parameters["postId"]!!.toLong()
        DeletePostUseCase()(postId)
    }
}

fun Route.getPostRoute() {
    get("/posts/{postId}") {
        val postId = call.parameters["postId"]!!.toLong()
        val post = GetPostUseCase()(postId)
        call.respond(post)
    }
}

fun Route.getPostsRoute() {
    get("/posts") {
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 25
        val since = call.request.queryParameters["since"]?.toLong()
        val resultPage = GetPostsUseCase()(limit, since)
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
