package com.kleinstein.server.presentation.routes

import com.kleinstein.server.domain.entities.NewComment
import com.kleinstein.server.domain.usecases.GetCommentsUseCase
import com.kleinstein.server.domain.usecases.NewCommentUseCase
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getCommentsRoute() {
    get("/posts/{postId}/comments") {
        val postId = call.parameters["postId"]!!.toLong()
        val page = call.request.queryParameters["page"]?.toInt() ?: 1
        val size = call.request.queryParameters["size"]?.toInt() ?: 25
        val resultPage = GetCommentsUseCase()(postId, page, size)
        call.respond(resultPage)
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