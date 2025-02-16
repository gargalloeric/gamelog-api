package com.mocosoft.plugins

import com.mocosoft.db.GameLogTable
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

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

    transaction {
        exec("CREATE TYPE GameState AS ENUM ('COMPLETED', 'IN_PROGRESS', 'PLANNED');")
        SchemaUtils.create(GameLogTable)
    }
}