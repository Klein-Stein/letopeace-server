package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.LiteUser
import com.kleinstein.server.domain.entities.Page
import com.kleinstein.server.domain.gateways.IDatabaseGateway

class GetUsersUseCase(db: IDatabaseGateway) : UseCase(db) {

    operator fun invoke(limit: Int, since: Long?): Page<LiteUser> {
        return this.db.getUserPage(limit, since)
    }
}
