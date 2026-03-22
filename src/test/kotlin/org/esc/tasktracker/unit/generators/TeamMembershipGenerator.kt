package org.esc.tasktracker.unit.generators

import org.esc.tasktracker.entities.TeamMembership
import org.esc.tasktracker.entities.Teams
import org.esc.tasktracker.entities.Users
import org.esc.tasktracker.enums.TeamRoles
import kotlin.random.Random

fun createTeamMembership(
    id: Long? = null,
    user: Users? = null,
    team: Teams? = null,
    role: TeamRoles? = null,
): TeamMembership {
    return TeamMembership(
        id = id ?: Random.nextLong(),
        user = user ?: createUser(),
        team = team ?: createTeam(),
        role = role ?: TeamRoles.MEMBER,
        createdAt = null,
        updatedAt = null,
    )
}