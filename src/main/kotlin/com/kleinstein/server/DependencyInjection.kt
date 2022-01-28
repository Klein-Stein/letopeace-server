package com.kleinstein.server

import com.kleinstein.server.data.gateways.db.DatabaseGateway
import com.kleinstein.server.domain.gateways.IDatabaseGateway
import com.kleinstein.server.domain.usecases.*
import io.ktor.application.*
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.di
import org.kodein.di.provider
import org.kodein.di.singleton

fun Application.installDi() {
    di {
        bind {
            singleton {
                val url = environment.config.property("ktor.database.connection.url").getString()
                val poolSize = environment.config.property("ktor.database.connection.pool").getString().toInt()
                return@singleton initDatabase(environment, url, poolSize)
            }
        }
        bind { provider { DeleteCommentLikeUseCase(instance()) } }
        bind { provider { DeleteCommentUseCase(instance()) } }
        bind { provider { DeletePostLikeUseCase(instance()) } }
        bind { provider { DeletePostUseCase(instance()) } }
        bind { provider { DeleteUserUseCase(instance()) } }
        bind { provider { GetCommentLikesUseCase(instance()) } }
        bind { provider { GetCommentsUseCase(instance()) } }
        bind { provider { GetPostLikesUseCase(instance()) } }
        bind { provider { GetPostUseCase(instance()) } }
        bind { provider { GetPostsUseCase(instance()) } }
        bind { provider { GetUsersUseCase(instance()) } }
        bind { provider { GetUserUseCase(instance()) } }
        bind { provider { NewCommentLikeUseCase(instance()) } }
        bind { provider { NewCommentUseCase(instance()) } }
        bind { provider { NewPostLikeUseCase(instance()) } }
        bind { provider { NewPostUseCase(instance()) } }
        bind { provider { NewUserUseCase(instance()) } }
        bind { provider { UpdateUserUseCase(instance()) } }
    }
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
