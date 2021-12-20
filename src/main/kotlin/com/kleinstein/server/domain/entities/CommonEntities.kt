package com.kleinstein.server.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Page<TEntity>(val page: Long, val total: Long, val items: List<TEntity>)
