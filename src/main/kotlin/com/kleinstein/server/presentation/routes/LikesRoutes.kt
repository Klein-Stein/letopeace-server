package com.kleinstein.server.presentation.routes

import com.kleinstein.server.domain.gateways.IDatabaseGateway
import com.kleinstein.server.domain.usecases.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.deleteCommentLikeRoute() {
    delete("/comments/likes/{likeId}") {
        val likeId = call.parameters["likeId"]!!.toLong()
        val db by closestDI().instance<IDatabaseGateway>()
        DeleteCommentLikeUseCase(db)(likeId)
    }
}

fun Route.deletePostLikeRoute() {
    delete("/posts/likes/{likeId}") {
        val likeId = call.parameters["likeId"]!!.toLong()
        val db by closestDI().instance<IDatabaseGateway>()
        DeletePostLikeUseCase(db)(likeId)
    }
}

fun Route.getCommentLikesRoute() {
    get("/comments/{commentId}/likes") {
        val commentId = call.parameters["commentId"]!!.toLong()
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 25
        val since = call.request.queryParameters["since"]?.toLong()
        val db by closestDI().instance<IDatabaseGateway>()
        val resultPage = GetCommentLikesUseCase(db)(commentId, limit, since)
        call.respond(resultPage)
    }
}

fun Route.getPostLikesRoute() {
    get("/posts/{postId}/likes") {
        val postId = call.parameters["postId"]!!.toLong()
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 25
        val since = call.request.queryParameters["since"]?.toLong()
        val db by closestDI().instance<IDatabaseGateway>()
        val resultPage = GetPostLikesUseCase(db)(postId, limit, since)
        call.respond(resultPage)
    }
}

fun Route.postCommentLikeRoute() {
    post("/comments/{commentId}/like") {
        val commentId = call.parameters["commentId"]!!.toLong()
        val createdBy = call.request.queryParameters["created_by"]!!.toLong()
        val db by closestDI().instance<IDatabaseGateway>()
        val like = NewCommentLikeUseCase(db)(commentId, createdBy)
        call.respond(like)
    }
}

fun Route.postPostLikeRoute() {
    post("/posts/{postId}/like") {
        val postId = call.parameters["postId"]!!.toLong()
        val createdBy = call.request.queryParameters["createdBy"]!!.toLong()
        val db by closestDI().instance<IDatabaseGateway>()
        val like = NewPostLikeUseCase(db)(postId, createdBy)
        call.respond(like)
    }
}
