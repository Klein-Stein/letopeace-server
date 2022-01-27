package com.kleinstein.server.data.gateways.db

import com.kleinstein.server.data.gateways.db.tables.*
import com.kleinstein.server.domain.entities.*
import com.kleinstein.server.domain.exceptions.DatabaseException
import com.kleinstein.server.domain.gateways.IDatabaseGateway
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.datetime.Clock
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.SQLException

/**
 * This is a IDatabaseGateway implementation.
 * Database version: v1.0
 */
class DatabaseGateway(url: String, poolSize: Int): IDatabaseGateway {
    private var dataSource: HikariDataSource

    init {
        val config = HikariConfig().apply {
            jdbcUrl = url
            isAutoCommit = false
            maximumPoolSize = poolSize
            validate()
        }
        dataSource = HikariDataSource(config)
    }

    override fun connect() {
        Database.connect(this.dataSource)
    }

    override fun disconnect() {
        try {
            this.dataSource.close()
        } catch (ex: SQLException) {
            throw(DatabaseException("Failed to properly close the database connection"))
        }
    }

    override fun addComment(newComment: NewComment, postId: Long, createdBy: Long): Comment {
        return transaction {
            val row = CommentTable.insert {
                it[this.postId] = postId
                it[msg] = newComment.msg
                it[this.createdBy] = createdBy
            }.resultedValues?.single()
            PostTable.update({ PostTable.id eq postId }) {
                with(SqlExpressionBuilder) {
                    it.update(commentsCount, commentsCount + 1)
                }
            }
            if (row != null) {
                return@transaction row.toComment()
            }
            throw DatabaseException("Failed to insert a comment")
        }
    }

    override fun addCommentLike(commentId: Long, createdBy: Long): Like {
        return transaction {
            val row = CommentLikeTable.insert {
                it[this.commentId] = commentId
                it[this.createdBy] = createdBy
            }.resultedValues?.single()
            CommentTable.update({ CommentTable.id eq commentId }) {
                with(SqlExpressionBuilder) {
                    it.update(likesCount, likesCount + 1)
                }
            }
            if (row != null) {
                return@transaction row.toCommentLike()
            }
            throw DatabaseException("Failed to insert a comment like")
        }
    }

    override fun addPost(newPost: NewPost, createdBy: Long): Post {
        return transaction {
            val row = PostTable.insert {
                it[msg] = newPost.msg
                it[this.createdBy] = createdBy
            }.resultedValues?.single()
            if (row != null) {
                return@transaction row.toPost()
            }
            throw DatabaseException("Failed to insert a post")
        }
    }

    override fun addPostLike(postId: Long, createdBy: Long): Like {
        return transaction {
            val row = PostLikeTable.insert {
                it[this.postId] = postId
                it[this.createdBy] = createdBy
            }.resultedValues?.single()
            PostTable.update({ PostTable.id eq postId }) {
                with(SqlExpressionBuilder) {
                    it.update(likesCount, likesCount + 1)
                }
            }
            if (row != null) {
                return@transaction row.toPostLike()
            }
            throw DatabaseException("Failed to insert a post like")
        }
    }

    override fun addUser(newUser: NewUser): User {
        return transaction {
            val row = UserTable.insert {
                it[firstName] = newUser.firstName
                it[lastName] = newUser.lastName
                it[nickname] = newUser.nickname
                it[genderId] = newUser.gender?.id
                it[dateOfBirth] = newUser.dateOfBirth
                it[phone] = newUser.phone
                it[email] = newUser.email
            }.resultedValues?.single()
            if (row != null) {
                return@transaction row.toUser()
            }
            throw DatabaseException("Failed to insert a user")
        }
    }

    override fun deleteComment(id: Long) {
        return transaction {
            val postId = CommentTable.slice(CommentTable.postId).select {
                CommentTable.id eq id
            }.single()[CommentTable.postId]
            CommentTable.deleteWhere { CommentTable.id eq id }
            PostTable.update({ PostTable.id eq postId }) {
                with(SqlExpressionBuilder) {
                    it.update(commentsCount, commentsCount - 1)
                }
            }
        }
    }

    override fun deleteCommentLike(commentId: Long, createdBy: Long) {
        return transaction {
            CommentLikeTable.deleteWhere {
                (CommentLikeTable.commentId eq commentId) and (CommentLikeTable.createdBy eq createdBy)
            }
            CommentTable.update({ CommentTable.id eq commentId }) {
                with(SqlExpressionBuilder) {
                    it.update(likesCount, likesCount - 1)
                }
            }
        }
    }

    override fun deletePost(id: Long) {
        return transaction {
            PostTable.deleteWhere { PostTable.id eq id }
        }
    }

    override fun deletePostLike(postId: Long, createdBy: Long) {
        return transaction {
            PostLikeTable.deleteWhere {
                (PostLikeTable.postId eq postId) and (PostLikeTable.createdBy eq createdBy)
            }
            PostTable.update({ PostTable.id eq postId }) {
                with(SqlExpressionBuilder) {
                    it.update(likesCount, likesCount - 1)
                }
            }
        }
    }

    override fun deleteUser(id: Long) {
        transaction {
            UserTable.update({ UserTable.id eq id }) {
                it[isDeleted] = true
            }
        }
    }

    override fun getCommentPage(postId: Long, limit: Int, since: Long): Page<Comment> {
        return transaction {
            val comments = CommentTable.select {
                (CommentTable.postId eq postId) and (CommentTable.id greater since)
            }.limit(limit).orderBy(CommentTable.id).map {
                it.toComment()
            }
            Page(
                data = comments,
                next = comments.maxOfOrNull { it.id }
            )
        }
    }

    override fun getCommentLikePage(commentId: Long, limit: Int, since: Long): Page<Like> {
        return transaction {
            val likes = CommentLikeTable.select {
                (CommentLikeTable.commentId eq commentId) and (CommentTable.id greater since)
            }.limit(limit).orderBy(CommentLikeTable.id).map {
                it.toCommentLike()
            }
            Page(
                data = likes,
                next = likes.maxOfOrNull { it.id }
            )
        }
    }

    override fun getPost(postId: Long): Post {
        return transaction {
            PostTable.select { PostTable.id eq postId }.single().toPost()
        }
    }

    override fun getPostPage(limit: Int, since: Long): Page<Post> {
        return transaction {
            val posts = PostTable.select {
                PostTable.id greater since
            }.limit(limit).orderBy(PostTable.id).map {
                it.toPost()
            }
            Page(
                data = posts,
                next = posts.maxOfOrNull { it.id }
            )
        }
    }

    override fun getPostLikePage(postId: Long, limit: Int, since: Long): Page<Like> {
        return transaction {
            val likes = PostLikeTable.select {
                (PostLikeTable.postId eq postId) and (PostLikeTable.id greater since)
            }.limit(limit).orderBy(PostLikeTable.id).map {
                it.toPostLike()
            }
            Page(
                data = likes,
                next = likes.maxOfOrNull { it.id }
            )
        }
    }

    override fun getUser(userId: Long): User {
        return transaction {
            UserTable.select { UserTable.id eq userId }.single().toUser()
        }
    }

    override fun getUserPage(limit: Int, since: Long): Page<LiteUser> {
        return transaction {
            val users = UserTable.select {
                UserTable.id greater since
            }.limit(limit).orderBy(UserTable.id).map {
                it.toLiteUser()
            }
            Page(
                data = users,
                next = users.maxOfOrNull { it.id }
            )
        }
    }

    override fun updateUser(user: UserUpdate, userId: Long): User {
        return transaction {
            UserTable.update({ UserTable.id eq userId }) {
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[nickname] = user.nickname
                it[genderId] = user.gender?.id
                it[dateOfBirth] = user.dateOfBirth
                it[phone] = user.phone
                it[email] = user.email
                it[modifiedAt] = Clock.System.now()
            }
            return@transaction getUser(userId)
        }
    }
}
