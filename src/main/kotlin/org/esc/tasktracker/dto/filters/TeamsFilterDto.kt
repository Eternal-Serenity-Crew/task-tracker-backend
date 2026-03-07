package org.esc.tasktracker.dto.filters

import org.esc.tasktracker.interfaces.FilterDtoClass

data class TeamsFilterDto(
    val name: String?,
    val ownerId: Long?,
) : FilterDtoClass
