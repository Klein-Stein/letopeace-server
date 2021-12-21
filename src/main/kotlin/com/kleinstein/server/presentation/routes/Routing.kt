package com.kleinstein.server.presentation.routes

import io.ktor.application.*
import io.ktor.routing.*

fun Application.installRoutes() {
    routing {
        route("/api/v1") {
            getCommentsRoute()
            getPostsRoute()
            postCommentRoute()
            postPostRoute()
        }
    }
}