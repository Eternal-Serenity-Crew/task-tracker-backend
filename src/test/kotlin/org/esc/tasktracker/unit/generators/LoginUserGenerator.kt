package org.esc.tasktracker.unit.generators

import org.esc.tasktracker.dto.auth.LoginUserDto

fun createLoginUserDto(email: String? = null, password: String? = null) = LoginUserDto(
    email = email ?: "test@mail.ru",
    password = password ?: "test_password"
)