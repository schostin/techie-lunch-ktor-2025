package de.sebastianneb.dev.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val statusCode: String,
    val cause: String?
)
