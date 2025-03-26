package de.sebastianneb.dev

import de.sebastianneb.dev.controller.PersonController
import de.sebastianneb.dev.model.ErrorResponse
import de.sebastianneb.dev.repository.PersonRepository
import de.sebastianneb.dev.service.PersonService
import io.ktor.serialization.kotlinx.json.json
import io.ktor.serialization.kotlinx.xml.xml
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(
            module {
                single<PersonController> {
                    PersonController(personService = get())
                }
                single<PersonService> {
                    PersonService(personRepository = get())
                }
                single<PersonRepository> { PersonRepository() }
            }
        )
    }
    install(ContentNegotiation) {
        json()
        xml()
    }
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(ErrorResponse(statusCode = "500", cause = cause.message))
        }
    }
}
