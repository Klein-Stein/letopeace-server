package com.kleinstein.server.domain.entities

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val nickname: String,
    val phone: String,
    val email: String?,
    val dateOfBirth: LocalDate,
    val gender: EGender?,
    val isDeleted: Boolean,
    val createdAt: Instant,
    val modifiedAt: Instant,
)
