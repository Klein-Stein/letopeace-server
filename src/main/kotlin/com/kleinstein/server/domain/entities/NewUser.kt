package com.kleinstein.server.domain.entities

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class NewUser(
    val firstName: String,
    val lastName: String,
    val nickname: String,
    val phone: String,
    val email: String?,
    val dateOfBirth: LocalDate,
    val gender: EGender?,
)
