package com.kleinstein.server.presentation.routes

import com.kleinstein.server.domain.usecases.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.deleteCommentLikeRoute() {
    delete("/comments/likes/{likeId}") {
        val likeId = call.parameters["likeId"]!!.toLong()
        DeleteCommentLikeUseCase()(likeId)
    }
}

fun Route.deletePostLikeRoute() {
    delete("/posts/likes/{likeId}") {
        val likeId = call.parameters["likeId"]!!.toLong()
        DeletePostLikeUseCase()(likeId)
    }
}

fun Route.getCommentLikesRoute() {
    get("/comments/{commentId}/likes") {
        val commentId = call.parameters["commentId"]!!.toLong()
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 25
        val since = call.request.queryParameters["since"]?.toLong()
        val resultPage = GetCommentLikesUseCase()(commentId, limit, since)
        call.respond(resultPage)
    }
}

fun Route.getPostLikesRoute() {
    get("/posts/{postId}/likes") {
        val postId = call.parameters["postId"]!!.toLong()
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 25
        val since = call.request.queryParameters["since"]?.toLong()
        val resultPage = GetPostLikesUseCase()(postId, limit, since)
        call.respond(resultPage)
    }
}

fun Route.postCommentLikeRoute() {
    post("/comments/{commentId}/like") {
        val commentId = call.parameters["commentId"]!!.toLong()
        val createdBy = call.request.queryParameters["created_by"]!!.toLong()
        val like = NewCommentLikeUseCase()(commentId, createdBy)
        call.respond(like)
    }
}

fun Route.postPostLikeRoute() {
    post("/posts/{postId}/like") {
        val postId = call.parameters["postId"]!!.toLong()
        val createdBy = call.request.queryParameters["createdBy"]!!.toLong()
        val like = NewPostLikeUseCase()(postId, createdBy)
        call.respond(like)
    }
}
