package org.esc.tasktracker.dto.teams

import org.esc.tasktracker.enums.TeamRoles
import org.esc.tasktracker.interfaces.DtoClass

data class CreateTeamMembershipDto(
    val userId: Long,
    val teamId: Long,
    val role: TeamRoles,
) : DtoClass
