package com.kleinstein.server.data.gateways.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object UserTable: Table("users") {
    val id = long("id").autoIncrement()
    val firstName = varchar("first_name", 128)
    val lastName = varchar("last_name", 128)
    val nickname = varchar("nickname", 128)
    val phone = varchar("phone", 15)
    val email = varchar("email", 320).nullable()
    val dateOfBirth = date("date_of_birth")
    val genderId = (integer("gender_id") references GenderTable.id).nullable()
    val isDeleted = bool("is_deleted")
    val createdAt = timestamp("created_at")
    val modifiedAt = timestamp("modified_at")
    override val primaryKey = PrimaryKey(id)
}
