val koin_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.1.20"
    id("io.ktor.plugin") version "3.1.1"
    kotlin("plugin.serialization") version "2.1.0"
}

group = "de.sebastianneb.dev"
version = "0.0.1"

application {
    mainClass = "de.sebastianneb.dev.ApplicationKt"

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-request-validation")
    implementation("io.ktor:ktor-server-call-logging")
    implementation("io.ktor:ktor-server-call-id")
    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    implementation("io.ktor:ktor-serialization-kotlinx-xml")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    implementation("io.ktor:ktor-server-jetty-jakarta")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-serialization-gson:3.1.1")
    implementation("io.ktor:ktor-server-auth")
    implementation("io.ktor:ktor-server-status-pages")
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
