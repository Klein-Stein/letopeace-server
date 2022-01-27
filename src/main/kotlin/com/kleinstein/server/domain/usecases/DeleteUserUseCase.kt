package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.gateways.IDatabaseGateway

class DeleteUserUseCase(db: IDatabaseGateway) : UseCase(db) {
    operator fun invoke(userId: Long) {
        this.db.deleteUser(userId)
    }
}
