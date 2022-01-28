package com.kleinstein.server.presentation.routes

import com.kleinstein.server.domain.usecases.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.deleteCommentLikeRoute() {
    delete("/comments/likes/{likeId}") {
        val likeId = call.parameters["likeId"]!!.toLong()
        val useCase by closestDI().instance<DeleteCommentLikeUseCase>()
        useCase(likeId)
    }
}

fun Route.deletePostLikeRoute() {
    delete("/posts/likes/{likeId}") {
        val likeId = call.parameters["likeId"]!!.toLong()
        val useCase by closestDI().instance<DeletePostLikeUseCase>()
        useCase(likeId)
    }
}

fun Route.getCommentLikesRoute() {
    get("/comments/{commentId}/likes") {
        val commentId = call.parameters["commentId"]!!.toLong()
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 25
        val since = call.request.queryParameters["since"]?.toLong()
        val useCase by closestDI().instance<GetCommentLikesUseCase>()
        val resultPage = useCase(commentId, limit, since)
        call.respond(resultPage)
    }
}

fun Route.getPostLikesRoute() {
    get("/posts/{postId}/likes") {
        val postId = call.parameters["postId"]!!.toLong()
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 25
        val since = call.request.queryParameters["since"]?.toLong()
        val useCase by closestDI().instance<GetPostLikesUseCase>()
        val resultPage = useCase(postId, limit, since)
        call.respond(resultPage)
    }
}

fun Route.postCommentLikeRoute() {
    post("/comments/{commentId}/likes") {
        val commentId = call.parameters["commentId"]!!.toLong()
        val createdBy = call.request.queryParameters["createdBy"]!!.toLong()
        val useCase by closestDI().instance<NewCommentLikeUseCase>()
        val like = useCase(commentId, createdBy)
        call.respond(like)
    }
}

fun Route.postPostLikeRoute() {
    post("/posts/{postId}/likes") {
        val postId = call.parameters["postId"]!!.toLong()
        val createdBy = call.request.queryParameters["createdBy"]!!.toLong()
        val useCase by closestDI().instance<NewPostLikeUseCase>()
        val like = useCase(postId, createdBy)
        call.respond(like)
    }
}
