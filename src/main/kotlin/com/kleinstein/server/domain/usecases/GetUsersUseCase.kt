package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.Page
import com.kleinstein.server.domain.entities.User

class GetUsersUseCase {
    operator fun invoke(limit: Int, since: Long?): Page<User> {
        return Page(
            data = arrayListOf(),
            next = null
        )
    }
}
