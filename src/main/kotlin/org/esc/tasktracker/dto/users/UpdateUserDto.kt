package org.esc.tasktracker.dto.users

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class UpdateUserDto(
    val id: Long,
    @Size(min = 2, max = 50) val name: String?,
    @Email val email: String?,
)
