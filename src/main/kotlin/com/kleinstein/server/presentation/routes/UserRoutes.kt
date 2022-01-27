package com.kleinstein.server.presentation.routes

import com.kleinstein.server.domain.entities.NewUser
import com.kleinstein.server.domain.entities.UserUpdate
import com.kleinstein.server.domain.gateways.IDatabaseGateway
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
        val db by closestDI().instance<IDatabaseGateway>()
        DeleteUserUseCase(db)(userId)
    }
}

fun Route.getUserRoute() {
    get("/users/{userId}") {
        val userId = call.parameters["userId"]!!.toLong()
        val db by closestDI().instance<IDatabaseGateway>()
        val user = GetUserUseCase(db)(userId)
        call.respond(user)
    }
}

fun Route.putUserRoute() {
    post("/users/{userId}") {
        val userId = call.parameters["userId"]!!.toLong()
        val data = call.receive<UserUpdate>()
        val db by closestDI().instance<IDatabaseGateway>()
        val user = UpdateUserUseCase(db)(userId, data)
        call.respond(user)
    }
}

fun Route.getUsersRoute() {
    get("/users") {
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 25
        val since = call.request.queryParameters["since"]?.toLong()
        val db by closestDI().instance<IDatabaseGateway>()
        val resultPage = GetUsersUseCase(db)(limit, since)
        call.respond(resultPage)
    }
}

fun Route.postUserRoute() {
    post("/users") {
        val newUser = call.receive<NewUser>()
        val db by closestDI().instance<IDatabaseGateway>()
        val user = NewUserUseCase(db)(newUser)
        call.respond(user)
    }
}
