package com.kleinstein.server.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Page<TEntity>(val data: List<TEntity>, val next: Long?)
