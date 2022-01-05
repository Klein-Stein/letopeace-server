package com.kleinstein.server.domain.gateways

import com.kleinstein.server.domain.entities.*
import com.kleinstein.server.domain.exceptions.GatewayException

interface IDatabaseGateway: IGateway {

    @Throws(GatewayException::class)
    fun addComment(comment: NewComment, postId: Long, createdBy: Long): Comment

    @Throws(GatewayException::class)
    fun addCommentLike(commentId: Long, createdBy: Long): Like

    @Throws(GatewayException::class)
    fun addPost(post: NewPost, createdBy: Long): Post

    @Throws(GatewayException::class)
    fun addPostLike(postId: Long, createdBy: Long): Like

    @Throws(GatewayException::class)
    fun addUser(user: NewUser): User

    @Throws(GatewayException::class)
    fun deleteComment(id: Long)

    @Throws(GatewayException::class)
    fun deleteCommentLike(commentId: Long, createdBy: Long)

    @Throws(GatewayException::class)
    fun deletePost(id: Long)

    @Throws(GatewayException::class)
    fun deletePostLike(postId: Long, createdBy: Long)

    @Throws(GatewayException::class)
    fun deleteUser(id: Long)

    @Throws(GatewayException::class)
    fun getCommentPage(postId: Long, page: Int, size: Int): Page<Comment>

    @Throws(GatewayException::class)
    fun getCommentLikePage(commentId: Long, page: Int, size: Int): Page<Like>

    @Throws(GatewayException::class)
    fun getPost(postId: Long): Post

    @Throws(GatewayException::class)
    fun getPostPage(page: Int, size: Int): Page<Post>

    @Throws(GatewayException::class)
    fun getPostLikePage(postId: Long, page: Int, size: Int): Page<Like>

    @Throws(GatewayException::class)
    fun getUser(userId: Long): User

    @Throws(GatewayException::class)
    fun getUserPage(userId: Long): Page<LiteUser>

    @Throws(GatewayException::class)
    fun updateUser(user: UserUpdate): User
}