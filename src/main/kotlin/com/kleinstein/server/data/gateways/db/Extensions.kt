package com.kleinstein.server.data.gateways.db

import com.kleinstein.server.data.gateways.db.tables.*
import com.kleinstein.server.domain.entities.*
import org.jetbrains.exposed.sql.*


fun ResultRow.toComment(): Comment {
    return Comment(
        id = this[CommentTable.id],
        postId = this[CommentTable.postId],
        msg = this[CommentTable.msg],
        likesCount = this[CommentTable.likesCount],
        createdBy = this[CommentTable.createdBy],
        createdAt = this[CommentTable.createdAt],
    )
}

fun ResultRow.toCommentLike(): Like {
    return Like(
        id = this[CommentLikeTable.id],
        createdBy = this[CommentLikeTable.createdBy],
        createdAt = this[CommentLikeTable.createdAt],
    )
}

fun ResultRow.toPost(): Post {
    return Post(
        id = this[PostTable.id],
        msg = this[PostTable.msg],
        likesCount = this[PostTable.likesCount],
        commentsCount = this[PostTable.commentsCount],
        createdBy = this[PostTable.createdBy],
        createdAt = this[PostTable.createdAt],
    )
}

fun ResultRow.toPostLike(): Like {
    return Like(
        id = this[PostLikeTable.id],
        createdBy = this[PostLikeTable.createdBy],
        createdAt = this[PostLikeTable.createdAt],
    )
}

fun ResultRow.toUser(): User {
    return User(
        id = this[UserTable.id],
        firstName = this[UserTable.firstName],
        lastName = this[UserTable.lastName],
        nickname = this[UserTable.nickname],
        dateOfBirth = this[UserTable.dateOfBirth],
        gender = this[UserTable.genderId]?.let { EGender.valueOf(it) },
        phone = this[UserTable.phone],
        email = this[UserTable.email],
        isDeleted = this[UserTable.isDeleted],
        createdAt = this[UserTable.createdAt],
        modifiedAt = this[UserTable.modifiedAt],
    )
}

fun ResultRow.toLiteUser(): LiteUser {
    return LiteUser(
        id = this[UserTable.id],
        firstName = this[UserTable.firstName],
        lastName = this[UserTable.lastName],
        nickname = this[UserTable.nickname],
        gender = this[UserTable.genderId]?.let { EGender.valueOf(it) },
    )
}

fun Query.andWhere(andPart: SqlExpressionBuilder.() -> Op<Boolean>) = adjustWhere {
    val expr = Op.build { andPart() }
    if(this == null) expr
    else this and expr
}
