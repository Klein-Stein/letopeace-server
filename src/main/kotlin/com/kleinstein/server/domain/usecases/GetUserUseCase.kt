package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.User
import com.kleinstein.server.domain.gateways.IDatabaseGateway

class GetUserUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(userId: Long): User {
        return this.db.getUser(userId)
    }
}
