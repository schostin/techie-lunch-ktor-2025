autoscale: true
slidenumbers: true
slide-transition: true
theme: Next
code-language: Kotlin

# Ktor: Building Modern Web Applications with Kotlin

---

### Ktor? Was ist das?

- Open Source framework entwickelt von JetBrains
- Asynchrone und high-performance web applications und APIs
- Kotlin-native (coroutines out-of-the-box)
- Einfach und simpel zu benutzen
- Sehr deklarativ

---

### Hauptmerkmale

- Flexibilität: Einfach erweiterbar durch Plugin
- Leistung: Coroutines ermöglichen hohe Performance und Skalierbarkeit

---

### Anwendungsgebiete

- Entwicklung von RESTful Web-APIs
- Erstellung von Microservices
- Serverless Computing (z.B. mit AWS Lambda)

---

### Grundlegende Konzepte

- Routing: Definition von HTTP-Routen und Endpunkten.
- Plugins: Erweiterung der Funktionalität durch installierbare Module.
- Engines: Verschiedene Server-Engines wie Netty, Jetty oder CIO.

---

### Performance

- Ktor startet innerhalb von 1-2 Millisekunden
- Sehr leichtgewichtig, und bspw. weniger Speicherverbrauch als SpringBoot.
- Eignet sich besonders für leichtgewichtige, hochperformante Microservices oder Anwendungen mit Fokus auf Effizienz.

---

## Ab in den Code!

---

### Was ich zeigen will

- Projekterstellung
- Server starten / Request absetzen (Hello World Beispiel)
  - Development Mode zeigen (Hot reloading)
- Routing eingehen
- Koin dependency injection
- Content Negotiation
- Testing (auch mit Koin)
- Security
  - Basic Authentication
  - JWT based
- Plugins
  - Call Logging
  - Status Pages

---

## Projekterstellung

- Projekt erstellen via IntelliJ
- Jetty server / Engine auswählen
- Ein paar Plugins auswählen
- Durch den erstellten Code gehen

---

## Server Starten / Request absetzen

- Server starten
- Sample request absetzen
- Development Mode aktivieren
  - io.ktor.development=true in development.env (Auto-rebuild aktivieren)

---

## Routing eingehen

- Einfach mal eine neue route erstellen

---

## Koin dependency injection

- Controller / Service / Repository erstellen (sample data class)  (Copy from sample Code)
- Injection in Koin
- Injection in Route (aufpassen, dass auf Application level)

---

## Content Negotiation

- Content Negotiation Abhängigkeiten in Gradle:

  ```kotlin
  implementation("io.ktor:ktor-server-content-negotiation")
  implementation("io.ktor:ktor-serialization-kotlinx-json")
  implementation("io.ktor:ktor-serialization-kotlinx-xml")
  ```
- Zusätzlich plugin installieren:

  ```kotlin
  kotlin("plugin.serialization") version "2.1.0"
  ```

- Installieren:

  ```kotlin
  install(ContentNegotiation) {
        json()
        xml()
    }
  ```

- Request versuchen mit -H "Accept: application/json" oder application/xml

---

## Testing

- Super easy, default setup funktioniert schon einwandfrei.
  - Koin mocking ist auch easy, rausziehen aus der Module Funktion und dann custom Koin scope
  - Unit Testing von bspw. Controller ohne größeren Aufwand
  - Kotest auch einfach möglich und integrierbar.
  - Wir haben dann auch zwischen Unit und Integration Tests unterschieden via Gradle, aber nix Ktor spezifisches

---

## Security

`implementation("io.ktor:ktor-server-auth")`

```kotlin
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

authenticate("auth-basic") {
  get("/personen") {
    call.respond(listOf(Person(vorname = "super", nachname = "secret")))
  }
}
```

---

## Plugins

---

### Call Logging

```kotlin
implementation("io.ktor:ktor-server-call-logging")
implementation("io.ktor:ktor-server-call-id")
```

```kotlin
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
```

---

### Status Pages

```kotlin
implementation("io.ktor:ktor-server-status-pages")
```

```kotlin
install(StatusPages) {
  exception<Throwable> { call, cause ->
    call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
  }
}
```

Natürlich auch custom exceptions möglich

---

Potentiell Euro-V Code anschauen

---

Fragen?

---

Danke fürs Zuhören.

GitHub Link:
