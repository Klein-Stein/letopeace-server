package com.kleinstein.server

import com.kleinstein.server.data.gateways.db.DatabaseGateway
import com.kleinstein.server.domain.gateways.IDatabaseGateway
import com.kleinstein.server.presentation.handlers.installErrorHandlers
import com.kleinstein.server.presentation.routes.installRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module(@Suppress("UNUSED_PARAMETER") testing: Boolean = false) {
    val db: IDatabaseGateway = DatabaseGateway(
        url = environment.config.property("ktor.database.connection.url").getString(),
        poolSize = environment.config.property("ktor.database.connection.pool").getString().toInt()
    )
    db.connect()
    environment.monitor.subscribe(ApplicationStopped) {
        db.disconnect()
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
        })
    }
    installErrorHandlers()
    installRoutes()
}