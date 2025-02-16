package com.mocosoft.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

const val USER_VARCHAR_LENGTH = 25
const val PLATFORMS_VARCHAR_LENGHT = 25

object GameLogTable : Table("gamelog") {
    val id = integer("id").autoIncrement()
    val user = varchar("user", USER_VARCHAR_LENGTH)
    val igdbID = integer("igdb_id")
    val state = customEnumeration("state","GameState", {value -> GameState.valueOf(value as String) }, { PGEnum("StateType", it) })
    val creationDate = datetime("creation_date").defaultExpression(CurrentDateTime)
    val platforms = varchar("platforms", PLATFORMS_VARCHAR_LENGHT)

    override val primaryKey = PrimaryKey(id)
}

enum class GameState {COMPLETED, IN_PROGRESS, PLANNED}

