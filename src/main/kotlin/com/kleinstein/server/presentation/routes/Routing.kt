package com.kleinstein.server.presentation.routes

import io.ktor.application.*
import io.ktor.routing.*

fun Application.installRoutes() {
    routing {
        route("/api/v1") {
            deleteCommentRoute()
            deleteCommentLikeRoute()
            deletePostRoute()
            deletePostLikeRoute()
            deleteUserRoute()

            getCommentsRoute()
            getCommentLikesRoute()
            getPostRoute()
            getPostLikesRoute()
            getPostsRoute()
            getUserRoute()
            getUsersRoute()

            postCommentRoute()
            postCommentLikeRoute()
            postPostRoute()
            postPostLikeRoute()
            postUserRoute()

            putUserRoute()
        }
    }
}
