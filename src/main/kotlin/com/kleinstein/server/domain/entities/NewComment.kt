package com.kleinstein.server.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class NewComment(val parentId: Long?, val msg: String, val createdBy: String)