package de.sebastianneb.dev.model

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val vorname: String,
    val nachname: String
)
