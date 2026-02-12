package org.esc.tasktracker.dto.jwt

import org.esc.tasktracker.interfaces.DtoClass

data class UserTokensDto(
    val accessToken: String,
    val refreshToken: String,
) : DtoClass
