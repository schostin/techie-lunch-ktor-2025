package de.sebastianneb.dev.service

import de.sebastianneb.dev.model.Person
import de.sebastianneb.dev.repository.PersonRepository

class PersonService(
    private val personRepository: PersonRepository
) {
    fun getPersonen(): List<Person> = personRepository.getPersonen()
}
