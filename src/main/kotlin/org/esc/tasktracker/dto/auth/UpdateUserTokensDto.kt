package org.esc.tasktracker.dto.auth

import org.esc.tasktracker.interfaces.DtoClass

data class UpdateUserTokensDto(
    val accessToken: String,
    val refreshToken: String,
) : DtoClass
