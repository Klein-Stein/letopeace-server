package com.kleinstein.server.presentation.routes

import com.kleinstein.server.domain.entities.NewComment
import com.kleinstein.server.domain.usecases.DeleteCommentUseCase
import com.kleinstein.server.domain.usecases.GetCommentsUseCase
import com.kleinstein.server.domain.usecases.NewCommentUseCase
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.deleteCommentRoute() {
    delete("/comments/{commentId}") {
        val commentId = call.parameters["commentId"]!!.toLong()
        DeleteCommentUseCase()(commentId)
    }
}

fun Route.postCommentRoute() {
    post("/posts/{postId}/comments") {
        val postId = call.parameters["postId"]!!.toLong()
        val newComment = call.receive<NewComment>()
        val comment = NewCommentUseCase()(postId, newComment)
        call.respond(comment)
    }
}

fun Route.getCommentsRoute() {
    get("/posts/{postId}/comments") {
        val postId = call.parameters["postId"]!!.toLong()
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 25
        val since = call.request.queryParameters["since"]?.toLong()
        val resultPage = GetCommentsUseCase()(postId, limit, since)
        call.respond(resultPage)
    }
}
