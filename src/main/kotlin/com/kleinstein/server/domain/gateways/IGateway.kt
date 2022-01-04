package com.kleinstein.server.domain.gateways

import com.kleinstein.server.domain.exceptions.GatewayException

interface IGateway {
    @Throws(GatewayException::class)
    fun connect()

    @Throws(GatewayException::class)
    fun disconnect()
}
