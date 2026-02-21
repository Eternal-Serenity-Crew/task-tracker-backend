package org.esc.tasktracker.unit.generators

import org.esc.tasktracker.dto.auth.UpdateUserTokensDto
import org.esc.tasktracker.dto.jwt.UserTokensDto

fun updateUsersTokensDto(accessToken: String? = null, refreshToken: String? = null) = UpdateUserTokensDto(
    accessToken = accessToken ?: "mock_access_token",
    refreshToken = refreshToken ?: "mock_refresh_token"
)