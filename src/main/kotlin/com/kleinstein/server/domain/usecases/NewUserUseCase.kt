package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.*
import com.kleinstein.server.domain.gateways.IDatabaseGateway

class NewUserUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(newUser: NewUser): User {
        return this.db.addUser(newUser)
    }
}
