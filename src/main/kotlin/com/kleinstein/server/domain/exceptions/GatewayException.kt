package com.kleinstein.server.domain.exceptions

class GatewayException: ServerException {
    constructor() : super()
    constructor(message: String?) : super(message)
}