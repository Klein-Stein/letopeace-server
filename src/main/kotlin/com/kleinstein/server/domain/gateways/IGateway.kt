package com.kleinstein.server.domain.gateways

import com.kleinstein.server.domain.exceptions.DatabaseException

interface IGateway {

    @Throws(DatabaseException::class)
    fun connect()

    @Throws(DatabaseException::class)
    fun disconnect()
}
