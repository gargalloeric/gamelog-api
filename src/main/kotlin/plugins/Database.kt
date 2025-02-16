package com.mocosoft.plugins

import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase() {
    val name = System.getenv("PG_DB")
    val user = System.getenv("PG_USER")
    val password = System.getenv("PG_PASSWORD")

    Database.connect(
        "jdbc:postgresql://localhost:5432/$name",
        driver = "org.postgresql.Driver",
        user = user,
        password = password
    )
}