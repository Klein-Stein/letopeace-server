package com.kleinstein.server.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class NewComment(val msg: String)
