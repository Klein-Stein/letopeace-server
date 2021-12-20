package com.kleinstein.server

import com.kleinstein.server.presentation.configureRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
        })
    }
    routing {
        route("/api/v1") {
            configureRoutes()
        }
    }
}