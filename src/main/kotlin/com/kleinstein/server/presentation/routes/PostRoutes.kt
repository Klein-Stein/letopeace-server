package com.kleinstein.server.presentation.routes

import com.kleinstein.server.domain.entities.NewPost
import com.kleinstein.server.domain.gateways.IDatabaseGateway
import com.kleinstein.server.domain.usecases.DeletePostUseCase
import com.kleinstein.server.domain.usecases.GetPostUseCase
import com.kleinstein.server.domain.usecases.GetPostsUseCase
import com.kleinstein.server.domain.usecases.NewPostUseCase
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.deletePostRoute() {
    delete("/posts/{postId}") {
        val postId = call.parameters["postId"]!!.toLong()
        val db by closestDI().instance<IDatabaseGateway>()
        DeletePostUseCase(db)(postId)
    }
}

fun Route.getPostRoute() {
    get("/posts/{postId}") {
        val postId = call.parameters["postId"]!!.toLong()
        val db by closestDI().instance<IDatabaseGateway>()
        val post = GetPostUseCase(db)(postId)
        call.respond(post)
    }
}

fun Route.getPostsRoute() {
    get("/posts") {
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 25
        val since = call.request.queryParameters["since"]?.toLong()
        val db by closestDI().instance<IDatabaseGateway>()
        val resultPage = GetPostsUseCase(db)(limit, since)
        call.respond(resultPage)
    }
}

fun Route.postPostRoute() {
    post("/posts") {
        val createdBy = call.request.queryParameters["created_by"]!!.toLong()
        val newPost = call.receive<NewPost>()
        val db by closestDI().instance<IDatabaseGateway>()
        val post = NewPostUseCase(db)(newPost, createdBy)
        call.respond(post)
    }
}
