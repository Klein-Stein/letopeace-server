package com.kleinstein.server.data.gateways.db.tables

import org.jetbrains.exposed.sql.Table

object GenderTable: Table("genders") {
    val id = integer("id")
    val name = varchar("name", 6)
    override val primaryKey = PrimaryKey(id)
}
