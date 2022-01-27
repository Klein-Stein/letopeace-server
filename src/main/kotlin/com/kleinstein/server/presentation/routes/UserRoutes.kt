package com.kleinstein.server.presentation.routes

import com.kleinstein.server.domain.entities.NewUser
import com.kleinstein.server.domain.entities.UserUpdate
import com.kleinstein.server.domain.usecases.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.deleteUserRoute() {
    delete("/users/{userId}") {
        val userId = call.parameters["userId"]!!.toLong()
        val useCase by closestDI().instance<DeleteUserUseCase>()
        useCase(userId)
    }
}

fun Route.getUserRoute() {
    get("/users/{userId}") {
        val userId = call.parameters["userId"]!!.toLong()
        val useCase by closestDI().instance<GetUserUseCase>()
        val user = useCase(userId)
        call.respond(user)
    }
}

fun Route.putUserRoute() {
    post("/users/{userId}") {
        val userId = call.parameters["userId"]!!.toLong()
        val data = call.receive<UserUpdate>()
        val useCase by closestDI().instance<UpdateUserUseCase>()
        val user = useCase(userId, data)
        call.respond(user)
    }
}

fun Route.getUsersRoute() {
    get("/users") {
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 25
        val since = call.request.queryParameters["since"]?.toLong()
        val useCase by closestDI().instance<GetUsersUseCase>()
        val resultPage = useCase(limit, since)
        call.respond(resultPage)
    }
}

fun Route.postUserRoute() {
    post("/users") {
        val newUser = call.receive<NewUser>()
        val useCase by closestDI().instance<NewUserUseCase>()
        val user = useCase(newUser)
        call.respond(user)
    }
}
