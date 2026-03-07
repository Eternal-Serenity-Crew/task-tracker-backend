package org.esc.tasktracker.dto.teams

import org.esc.tasktracker.interfaces.DtoClass

/**
 * Data Transfer Object for updating an existing team.
 *
 * All fields except [id] are optional - only provided fields will be updated.
 * Fields set to null will be ignored during update processing.
 *
 * @param id The unique identifier of the team to update. Required.
 * @param name New name for the team. If provided, will be trimmed automatically.
 * @param description New description for the team.
 * @param userId New owner ID for the team. Must reference an existing user.
 *
 * @see org.esc.tasktracker.entities.Teams
 * @see org.esc.tasktracker.services.TeamsService.update
 */
data class UpdateTeamDto(
    val id: Long,
    val name: String?,
    val description: String?,
    val userId: Long?,
) : DtoClass
