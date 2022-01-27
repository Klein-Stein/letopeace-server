package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.EGender
import com.kleinstein.server.domain.entities.User
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetUserUseCase {
    operator fun invoke(userId: Long): User {
        return User(
            id = userId,
            firstName = "",
            lastName = "",
            nickname = "",
            dateOfBirth = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
            gender = EGender.MALE,
            phone = "",
            email = "",
            isDeleted = false,
            createdAt = Clock.System.now(),
            modifiedAt = Clock.System.now(),
        )
    }
}
