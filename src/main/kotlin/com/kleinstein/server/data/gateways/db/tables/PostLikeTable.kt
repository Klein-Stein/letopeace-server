package com.kleinstein.server.data.gateways.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object PostLikeTable: Table("post_likes") {
    val id = long("id").autoIncrement()
    val postId = long("post_id") references PostTable.id
    val createdBy = long("created_by") references UserTable.id
    val createdAt = timestamp("created_at")
    override val primaryKey = PrimaryKey(id)
}
