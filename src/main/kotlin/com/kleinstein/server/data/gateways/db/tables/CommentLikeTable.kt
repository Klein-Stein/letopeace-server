package com.kleinstein.server.data.gateways.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object CommentLikeTable: Table("comment_likes") {
    val id = long("id").autoIncrement()
    val commentId = long("comment_id") references CommentTable.id
    val createdBy = long("created_by") references UserTable.id
    val createdAt = timestamp("created_at")
    override val primaryKey = PrimaryKey(id)
}
