package org.esc.tasktracker.dto.teams

import org.esc.tasktracker.interfaces.DtoClass

data class CreateTeamDto(
    val name: String,
    val description: String,
    val userId: Long,
) : DtoClass
