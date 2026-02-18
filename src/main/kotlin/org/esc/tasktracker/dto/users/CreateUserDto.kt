package org.esc.tasktracker.dto.users

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.esc.tasktracker.interfaces.DtoClass

data class CreateUserDto(
    @NotBlank @Size(min = 2, max = 50) val name: String,
    @Email val email: String,
    @NotBlank @Size(min = 8, max = 100) val password: String,
) : DtoClass
