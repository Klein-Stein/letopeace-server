package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.*
import com.kleinstein.server.domain.gateways.IDatabaseGateway

class UpdateUserUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(userId: Long, data: UserUpdate): User {
        return this.db.updateUser(userId, data)
    }
}
