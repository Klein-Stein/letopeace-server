package com.kleinstein.server.domain.exceptions

open class ServerException: RuntimeException {
    constructor() : super()
    constructor(message: String?) : super(message)
}