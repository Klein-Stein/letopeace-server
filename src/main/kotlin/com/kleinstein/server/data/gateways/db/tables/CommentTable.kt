package com.kleinstein.server.data.gateways.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object CommentTable: Table("comments") {
    val id = long("id").autoIncrement()
    val postId = long("post_id") references PostTable.id
    val msg = varchar("msg", 2048)
    val likesCount = integer("likes_count")
    val createdBy = long("created_by") references UserTable.id
    val createdAt = timestamp("created_at")
    override val primaryKey = PrimaryKey(id)
}
