package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Page
import com.kleinstein.server.domain.entities.User
import com.kleinstein.server.domain.gateways.IDatabaseGateway

class GetUsersUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(limit: Int, since: Long?): Page<User> {
        return Page(
            data = arrayListOf(),
            next = null
        )
    }
}
