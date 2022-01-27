package com.kleinstein.server

import com.kleinstein.server.data.gateways.db.DatabaseGateway
import com.kleinstein.server.domain.gateways.IDatabaseGateway
import com.kleinstein.server.presentation.handlers.installErrorHandlers
import com.kleinstein.server.presentation.routes.installRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module(@Suppress("UNUSED_PARAMETER") testing: Boolean = false) {
    di {
        bind {
            singleton {
                val url = environment.config.property("ktor.database.connection.url").getString()
                val poolSize = environment.config.property("ktor.database.connection.pool").getString().toInt()
                return@singleton initDatabase(environment, url, poolSize)
            }
        }
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
        })
    }
    installErrorHandlers()
    installRoutes()
}

fun initDatabase(environment: ApplicationEnvironment, url: String, poolSize: Int): IDatabaseGateway {
    val db: IDatabaseGateway = DatabaseGateway(
        url = url,
        poolSize = poolSize
    )
    db.connect()
    environment.monitor.subscribe(ApplicationStopped) {
        db.disconnect()
    }

    return db
}
