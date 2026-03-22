package org.esc.tasktracker.dto.filters

import org.esc.tasktracker.enums.TeamRoles
import org.esc.tasktracker.interfaces.FilterDtoClass

data class TeamMembershipFilterDto(
    val userId: Long?,
    val teamId: Long?,
    val teamRole: TeamRoles?
) : FilterDtoClass
