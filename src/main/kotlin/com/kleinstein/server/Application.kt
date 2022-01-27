package com.kleinstein.server

import com.kleinstein.server.presentation.handlers.installErrorHandlers
import com.kleinstein.server.presentation.routes.installRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module(@Suppress("UNUSED_PARAMETER") testing: Boolean = false) {
    installDi()
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
        })
    }
    installErrorHandlers()
    installRoutes()
}
