package org.esc.tasktracker.dto.jwt

import org.esc.tasktracker.interfaces.DtoClass

/**
 * Data transfer object for returning authentication tokens to clients.
 *
 * Contains the complete token pair issued to a client after successful
 * authentication, registration, or token refresh. This is the primary
 * response DTO for all authentication endpoints.
 *
 * @param accessToken Short-lived JWT token used for authorizing API requests.
 *                    Typically expires in minutes to hours. Must be included
 *                    in the Authorization header (Bearer scheme) for
 *                    authenticated requests.
 * @param refreshToken Long-lived JWT token used to obtain new access tokens
 *                     when the current one expires. Typically valid for days
 *                     to weeks. Should be stored securely by the client.
 *
 * @see org.esc.tasktracker.controllers.AuthController.register
 * @see org.esc.tasktracker.controllers.AuthController.login
 * @see org.esc.tasktracker.controllers.AuthController.updateTokens
 * @see org.esc.tasktracker.services.AuthService.generateTokens
 * @see org.esc.tasktracker.dto.auth.UpdateUserTokensDto
 * @see DtoClass
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
data class UserTokensDto(
    val accessToken: String,
    val refreshToken: String,
) : DtoClass
