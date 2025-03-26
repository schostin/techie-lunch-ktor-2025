package de.sebastianneb.dev

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.jakarta.Jetty

fun main() {
    embeddedServer(Jetty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureMonitoring()
    configureFrameworks()
    configureRouting()
}
