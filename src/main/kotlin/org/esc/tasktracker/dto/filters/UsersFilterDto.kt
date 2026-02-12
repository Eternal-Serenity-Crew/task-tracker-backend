package org.esc.tasktracker.dto.filters

import org.esc.tasktracker.interfaces.FilterDtoClass

data class UsersFilterDto(
    val name: String?,
    val email: String?,
) : FilterDtoClass
