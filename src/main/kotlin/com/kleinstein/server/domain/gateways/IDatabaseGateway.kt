package com.kleinstein.server.domain.gateways

import com.kleinstein.server.domain.entities.*
import com.kleinstein.server.domain.exceptions.DatabaseException

interface IDatabaseGateway: IGateway {

    @Throws(DatabaseException::class)
    fun addComment(postId: Long, newComment: NewComment, createdBy: Long): Comment

    @Throws(DatabaseException::class)
    fun addCommentLike(commentId: Long, createdBy: Long): Like

    @Throws(DatabaseException::class)
    fun addPost(newPost: NewPost, createdBy: Long): Post

    @Throws(DatabaseException::class)
    fun addPostLike(postId: Long, createdBy: Long): Like

    @Throws(DatabaseException::class)
    fun addUser(newUser: NewUser): User

    @Throws(DatabaseException::class)
    fun deleteComment(commentId: Long)

    @Throws(DatabaseException::class)
    fun deleteCommentLike(likeId: Long)

    @Throws(DatabaseException::class)
    fun deletePost(postId: Long)

    @Throws(DatabaseException::class)
    fun deletePostLike(likeId: Long)

    @Throws(DatabaseException::class)
    fun deleteUser(userId: Long)

    @Throws(DatabaseException::class)
    fun getCommentPage(postId: Long, limit: Int, since: Long?): Page<Comment>

    @Throws(DatabaseException::class)
    fun getCommentLikePage(commentId: Long, limit: Int, since: Long?): Page<Like>

    @Throws(DatabaseException::class)
    fun getPost(postId: Long): Post

    @Throws(DatabaseException::class)
    fun getPostPage(limit: Int, since: Long?): Page<Post>

    @Throws(DatabaseException::class)
    fun getPostLikePage(postId: Long, limit: Int, since: Long?): Page<Like>

    @Throws(DatabaseException::class)
    fun getUser(userId: Long): User

    @Throws(DatabaseException::class)
    fun getUserPage(limit: Int, since: Long?): Page<LiteUser>

    @Throws(DatabaseException::class)
    fun updateUser(userId: Long, data: UserUpdate): User
}