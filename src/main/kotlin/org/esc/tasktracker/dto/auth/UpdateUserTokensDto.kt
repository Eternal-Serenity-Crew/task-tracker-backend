package org.esc.tasktracker.dto.auth

import org.esc.tasktracker.interfaces.DtoClass

/**
 * Data transfer object for token refresh requests.
 *
 * Contains the token pair required to obtain new authentication tokens
 * without requiring the user to re-enter credentials. Used in the refresh
 * token flow when the access token has expired but the refresh token is still valid.
 *
 * @param accessToken The expired or soon-to-expire JWT access token.
 *                    May be validated but can be expired.
 * @param refreshToken The valid JWT refresh token used to obtain a new token pair.
 *                     Must be valid, not expired, and associated with the session.
 *
 * @see org.esc.tasktracker.controllers.AuthController.updateTokens
 * @see org.esc.tasktracker.services.AuthService.updateTokens
 * @see org.esc.tasktracker.dto.jwt.UserTokensDto
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
data class UpdateUserTokensDto(
    val accessToken: String,
    val refreshToken: String,
) : DtoClass
