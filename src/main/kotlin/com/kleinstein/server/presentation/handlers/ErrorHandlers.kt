package com.kleinstein.server.presentation.handlers

import com.kleinstein.server.presentation.models.ResponseError
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun Application.installErrorHandlers() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
            call.respond(ResponseError(
                code = it.value,
                summary = it.description
            ))
        }
    }
}