package de.sebastianneb.dev

import io.ktor.http.HttpHeaders
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.callid.CallId
import io.ktor.server.plugins.callid.callIdMdc
import io.ktor.server.plugins.calllogging.CallLogging

fun Application.configureMonitoring() {
    install(CallId) {
        header(HttpHeaders.XRequestId)
        generate { "123" }
        verify { callId: String ->
            callId.isNotEmpty()
        }
    }
    install(CallLogging) {
        callIdMdc("call-id")
    }
}
