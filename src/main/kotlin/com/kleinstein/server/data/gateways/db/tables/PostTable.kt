package com.kleinstein.server.data.gateways.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object PostTable: Table("posts") {
    val id = long("id").autoIncrement()
    val msg = varchar("msg", 4096)
    val likesCount = integer("likes_count")
    val commentsCount = integer("comments_count")
    val createdBy = long("created_by") references UserTable.id
    val createdAt = timestamp("created_at")
    override val primaryKey = PrimaryKey(id)
}
