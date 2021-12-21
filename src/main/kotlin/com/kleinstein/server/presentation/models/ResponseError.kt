package com.kleinstein.server.presentation.models

import kotlinx.serialization.Serializable

@Serializable
data class ResponseError(val code: Int, val summary: String, val message: String? = null)
