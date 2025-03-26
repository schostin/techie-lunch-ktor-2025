package de.sebastianneb.dev.controller

import de.sebastianneb.dev.model.Person
import de.sebastianneb.dev.service.PersonService

class PersonController(
    private val personService: PersonService
) {
    fun getPerson(): List<Person> = personService.getPersonen()
}
