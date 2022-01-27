package com.kleinstein.server.presentation.routes

import com.kleinstein.server.domain.entities.NewComment
import com.kleinstein.server.domain.gateways.IDatabaseGateway
import com.kleinstein.server.domain.usecases.DeleteCommentUseCase
import com.kleinstein.server.domain.usecases.GetCommentsUseCase
import com.kleinstein.server.domain.usecases.NewCommentUseCase
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.deleteCommentRoute() {
    delete("/comments/{commentId}") {
        val commentId = call.parameters["commentId"]!!.toLong()
        val db by closestDI().instance<IDatabaseGateway>()
        DeleteCommentUseCase(db)(commentId)
    }
}

fun Route.postCommentRoute() {
    post("/posts/{postId}/comments") {
        val postId = call.parameters["postId"]!!.toLong()
        val createdBy = call.request.queryParameters["created_by"]!!.toLong()
        val newComment = call.receive<NewComment>()
        val db by closestDI().instance<IDatabaseGateway>()
        val comment = NewCommentUseCase(db)(postId, newComment, createdBy)
        call.respond(comment)
    }
}

fun Route.getCommentsRoute() {
    get("/posts/{postId}/comments") {
        val postId = call.parameters["postId"]!!.toLong()
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 25
        val since = call.request.queryParameters["since"]?.toLong()
        val db by closestDI().instance<IDatabaseGateway>()
        val resultPage = GetCommentsUseCase(db)(postId, limit, since)
        call.respond(resultPage)
    }
}
