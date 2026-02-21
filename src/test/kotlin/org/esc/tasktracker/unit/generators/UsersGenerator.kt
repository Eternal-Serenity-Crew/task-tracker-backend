package org.esc.tasktracker.unit.generators

import org.esc.tasktracker.dto.users.CreateUserDto
import org.esc.tasktracker.dto.users.UpdateUserDto
import org.esc.tasktracker.entities.Users
import kotlin.math.absoluteValue
import kotlin.random.Random

fun createUser(id: Long? = null, name: String? = null, email: String? = null, password: String? = null): Users {
    return Users(
        id = id ?: Random.nextLong().absoluteValue,
        name = name ?: "Test name",
        email = email ?: "Test email",
        password = password ?: "password",
        createdAt = null,
        updatedAt = null
    )
}

fun createUserCreateDto(name: String? = null, email: String? = null, password: String? = null): CreateUserDto {
    return CreateUserDto(
        name = name ?: "Test name",
        email = email ?: "Test email",
        password = password ?: "password",
    )
}

fun createUserUpdateDto(id: Long? = null, name: String? = null, email: String? = null): UpdateUserDto {
    return UpdateUserDto(
        id = id ?: Random.nextLong().absoluteValue,
        name = name,
        email = email
    )
}