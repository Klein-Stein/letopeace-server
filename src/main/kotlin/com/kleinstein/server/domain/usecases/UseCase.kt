package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.gateways.IDatabaseGateway

abstract class UseCase(protected val db: IDatabaseGateway)
