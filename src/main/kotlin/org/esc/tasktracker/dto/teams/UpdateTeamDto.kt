package org.esc.tasktracker.dto.teams

import org.esc.tasktracker.interfaces.DtoClass

data class UpdateTeamDto(
    val id: Long,
    val name: String?,
    val description: String?,
    val userId: Long?,
) : DtoClass
