package com.mocosoft.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

const val MAX_EMAIL_LENGTH = 150
const val MAX_USER_NAME_LENGTH = 25
const val BCRYPT_HASH_LENGTH = 60
const val AVATAR_URL_LENGTH = 255

object UserTable : Table("users") {
    val id = integer("id").autoIncrement()
    val email = varchar("email", MAX_EMAIL_LENGTH).uniqueIndex()
    val name = varchar("name", MAX_USER_NAME_LENGTH)
    val password = varchar("password", BCRYPT_HASH_LENGTH)
    val avatar = varchar("avatar", AVATAR_URL_LENGTH)
    val creationDate = datetime("creation_date").defaultExpression(CurrentDateTime)
    val isActive = bool("is_active").default(true)

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}