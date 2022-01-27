package com.kleinstein.server.presentation.routes

import com.kleinstein.server.domain.entities.NewUser
import com.kleinstein.server.domain.entities.UserUpdate
import com.kleinstein.server.domain.usecases.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.deleteUserRoute() {
    delete("/users/{userId}") {
        val userId = call.parameters["userId"]!!.toLong()
        DeleteUserUseCase()(userId)
    }
}

fun Route.getUserRoute() {
    get("/users/{userId}") {
        val userId = call.parameters["userId"]!!.toLong()
        val user = GetUserUseCase()(userId)
        call.respond(user)
    }
}

fun Route.putUserRoute() {
    post("/users/{userId}") {
        val userId = call.parameters["userId"]!!.toLong()
        val userUpdate = call.receive<UserUpdate>()
        val user = UpdateUserUseCase()(userId, userUpdate)
        call.respond(user)
    }
}

fun Route.getUsersRoute() {
    get("/users") {
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 25
        val since = call.request.queryParameters["since"]?.toLong()
        val resultPage = GetUsersUseCase()(limit, since)
        call.respond(resultPage)
    }
}

fun Route.postUserRoute() {
    post("/users") {
        val newUser = call.receive<NewUser>()
        val user = NewUserUseCase()(newUser)
        call.respond(user)
    }
}
