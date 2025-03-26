package de.sebastianneb.dev

import de.sebastianneb.dev.controller.PersonController
import de.sebastianneb.dev.model.Person
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.basic
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    install(RequestValidation) {
        validate<String> { bodyText ->
            if (!bodyText.startsWith("Hello")) {
                ValidationResult.Invalid("Body text should start with 'Hello'")
            } else {
                ValidationResult.Valid
            }
        }
    }
    install(Authentication) {
        basic("auth-basic") {
            realm = "Access to the /super-secret path"
            validate { credentials ->
                if (credentials.name == "sebastian") {
                    UserIdPrincipal("sebastian")
                } else {
                    null
                }
            }
        }
    }
    val personController: PersonController by inject()
    routing {
        get("/") {
            val person = personController.getPerson()
            call.respond(person)
        }
        get("/personen") {
            call.respondText("TODO")
        }
        route("/super-secret") {
            authenticate("auth-basic") {
                get("/personen") {
                    call.respond(listOf(Person(vorname = "super", nachname = "secret")))
                }
                get("/error") {
                    throw RuntimeException("Foo")
                }
            }
        }
    }
}
