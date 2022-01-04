package com.kleinstein.server.data.gateways.db

import com.kleinstein.server.domain.exceptions.GatewayException
import com.kleinstein.server.domain.gateways.IDatabaseGateway
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


class PostgresqlGateway(private val url: String): IDatabaseGateway {
    lateinit var connection: Connection

    override fun connect() {
        try {
            this.connection = DriverManager.getConnection(url)
        } catch (ex: SQLException) {
            throw(GatewayException("Unable to connect to the database"))
        }
    }

    override fun disconnect() {
        try {
            this.connection.close()
        } catch (ex: SQLException) {
            throw(GatewayException("Failed to properly close the database connection"))
        }
    }
}