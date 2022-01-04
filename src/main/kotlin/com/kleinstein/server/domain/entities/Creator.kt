package com.kleinstein.server.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Creator(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val nickname: String,
    val gender: EGender?,
)
