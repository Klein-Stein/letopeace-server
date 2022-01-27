package com.kleinstein.server.domain.entities

enum class EGender(val id: Int) {
    FEMALE(1),
    MALE(2);

    companion object {
        fun valueOf(value: Int) = values().find { it.id == value }
    }
}
