package de.sebastianneb.dev

import de.sebastianneb.dev.model.Person
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() =
        testApplication {
            application {
                module()
            }
            client.get("/").apply {
                assertEquals(HttpStatusCode.OK, status)
                val personen = Json.decodeFromString<List<Person>>(bodyAsText())
                assertEquals(personen, listOf(Person(vorname = "Sebastian", nachname = "Neb")))
            }

            val response =
                client.get("/") {
                    header("Foo", "bar")
                }
        }
}
