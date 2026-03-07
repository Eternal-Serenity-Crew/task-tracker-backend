package org.esc.tasktracker.dto.teams

import org.esc.tasktracker.interfaces.DtoClass

/**
 * Data Transfer Object for creating a new team.
 *
 * @param name The name of the team. Required field, will be trimmed by entity listener.
 * @param description Optional detailed description of the team's purpose.
 * @param userId ID of the user who will own/create the team. Must reference an existing user.
 *
 * @see org.esc.tasktracker.entities.Teams
 * @see org.esc.tasktracker.services.TeamsService.create
 */
data class CreateTeamDto(
    val name: String,
    val description: String?,
    val userId: Long,
) : DtoClass
