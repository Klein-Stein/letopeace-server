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
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.deletePostRoute() {
    delete("/posts/{postId}") {
        val postId = call.parameters["postId"]!!.toLong()
        val useCase by closestDI().instance<DeletePostUseCase>()
        useCase(postId)
    }
}

fun Route.getPostRoute() {
    get("/posts/{postId}") {
        val postId = call.parameters["postId"]!!.toLong()
        val useCase by closestDI().instance<GetPostUseCase>()
        val post = useCase(postId)
        call.respond(post)
    }
}

fun Route.getPostsRoute() {
    get("/posts") {
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 25
        val since = call.request.queryParameters["since"]?.toLong()
        val useCase by closestDI().instance<GetPostsUseCase>()
        val resultPage = useCase(limit, since)
        call.respond(resultPage)
    }
}

fun Route.postPostRoute() {
    post("/posts") {
        val createdBy = call.request.queryParameters["createdBy"]!!.toLong()
        val newPost = call.receive<NewPost>()
        val useCase by closestDI().instance<NewPostUseCase>()
        val post = useCase(newPost, createdBy)
        call.respond(post)
    }
}
