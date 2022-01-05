package com.kleinstein.server.data.gateways.db

import com.kleinstein.server.domain.entities.*
import com.kleinstein.server.domain.exceptions.GatewayException
import com.kleinstein.server.domain.gateways.IDatabaseGateway
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/**
 * This is a IDatabaseGateway implementation for PostgreSQL.
 * Database version: v1.0
 */
class PostgresqlGateway(private val url: String): IDatabaseGateway {
    lateinit var connection: Connection

    override fun connect() {
        try {
            this.connection = DriverManager.getConnection(url)
        } catch (ex: SQLException) {
            throw(GatewayException("Unable to connect to the database"))
        }
    }

    override fun disconnect() {
        try {
            this.connection.close()
        } catch (ex: SQLException) {
            throw(GatewayException("Failed to properly close the database connection"))
        }
    }

    override fun addComment(comment: NewComment, postId: Long, createdBy: Long): Comment {
        TODO("Not yet implemented")
    }

    override fun addCommentLike(commentId: Long, createdBy: Long): Like {
        TODO("Not yet implemented")
    }

    override fun addPost(post: NewPost, createdBy: Long): Post {
        TODO("Not yet implemented")
    }

    override fun addPostLike(postId: Long, createdBy: Long): Like {
        TODO("Not yet implemented")
    }

    override fun addUser(user: NewUser): User {
        TODO("Not yet implemented")
    }

    override fun deleteComment(id: Long) {
        TODO("Not yet implemented")
    }

    override fun deleteCommentLike(commentId: Long, createdBy: Long) {
        TODO("Not yet implemented")
    }

    override fun deletePost(id: Long) {
        TODO("Not yet implemented")
    }

    override fun deletePostLike(postId: Long, createdBy: Long) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(id: Long) {
        TODO("Not yet implemented")
    }

    override fun getCommentPage(postId: Long, page: Int, size: Int): Page<Comment> {
        TODO("Not yet implemented")
    }

    override fun getCommentLikePage(commentId: Long, page: Int, size: Int): Page<Like> {
        TODO("Not yet implemented")
    }

    override fun getPost(postId: Long): Post {
        TODO("Not yet implemented")
    }

    override fun getPostPage(page: Int, size: Int): Page<Post> {
        TODO("Not yet implemented")
    }

    override fun getPostLikePage(postId: Long, page: Int, size: Int): Page<Like> {
        TODO("Not yet implemented")
    }

    override fun getUser(userId: Long): User {
        TODO("Not yet implemented")
    }

    override fun getUserPage(userId: Long): Page<LiteUser> {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: UserUpdate): User {
        TODO("Not yet implemented")
    }
}