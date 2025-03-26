package de.sebastianneb.dev.repository

import de.sebastianneb.dev.model.Person

class PersonRepository {
    fun getPersonen(): List<Person> = listOf(Person(vorname = "Sebastian", nachname = "Neb"))
}
