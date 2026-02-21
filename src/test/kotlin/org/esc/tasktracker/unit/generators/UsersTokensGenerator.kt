package org.esc.tasktracker.unit.generators

import org.esc.tasktracker.dto.jwt.UserTokensDto

fun createUsersTokensDto() = UserTokensDto(
    accessToken = "mock_access_token",
    refreshToken = "mock_refresh_token"
)