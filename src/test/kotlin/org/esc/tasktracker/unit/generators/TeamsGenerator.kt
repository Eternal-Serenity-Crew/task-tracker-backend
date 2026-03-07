package org.esc.tasktracker.unit.generators

import org.esc.tasktracker.dto.teams.CreateTeamDto
import org.esc.tasktracker.dto.teams.UpdateTeamDto
import org.esc.tasktracker.entities.Teams
import org.esc.tasktracker.entities.Users
import kotlin.random.Random

fun createTeam(
    id: Long? = null,
    name: String? = null,
    description: String? = null,
    user: Users? = null
): Teams {
    return Teams(
        id = id ?: Random.nextLong(),
        name = name ?: "Тестовое имя",
        description = description,
        owner = user ?: createUser(),
        createdAt = null,
        updatedAt = null
    )
}

fun createTeamCreateDto(name: String? = null, description: String? = null, userId: Long? = null): CreateTeamDto {
    return CreateTeamDto(
        name = name ?: "Тестовое имя",
        description = description,
        userId = userId ?: Random.nextLong(),
    )
}

fun createTeamUpdateDto(id: Long? = null, name: String? = null, description: String? = null, userId: Long? = null): UpdateTeamDto {
    return UpdateTeamDto(
        id = id ?: Random.nextLong(),
        name = name,
        description = description,
        userId = userId,
    )
}