package com.kleinstein.server.domain.usecases

import com.kleinstein.server.domain.entities.*
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random

class NewUserUseCase {
    operator fun invoke(newUser: NewUser): User {
        return User(
            id = Random.nextLong(1, Long.MAX_VALUE),
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
