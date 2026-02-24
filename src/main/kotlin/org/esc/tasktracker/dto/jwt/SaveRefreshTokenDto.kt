package org.esc.tasktracker.dto.jwt

import org.esc.tasktracker.entities.Users
import org.esc.tasktracker.interfaces.DtoClass
import java.util.UUID

/**
 * Data transfer object for persisting refresh tokens in the system.
 *
 * Contains all necessary information to store a refresh token in the database
 * for session management and token validation. Used internally by the
 * authentication service when issuing new refresh tokens.
 *
 * @param user The user entity associated with this refresh token.
 *             Establishes the relationship between the token and the user
 *             for quick lookup during token validation.
 * @param uuid Unique session identifier that binds this refresh token to
 *             a specific client session. Enables:
 *             - Multiple sessions per user
 *             - Session-specific token validation
 *             - Targeted token revocation (logout from specific device)
 * @param token The actual JWT refresh token string that will be stored.
 *              This is the token value that clients present when requesting
 *              new access tokens.
 *
 * @see org.esc.tasktracker.services.AuthService.generateTokens
 * @see Users
 * @see DtoClass
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
data class SaveRefreshTokenDto(
    val user: Users,
    val uuid: UUID,
    val token: String
) : DtoClass
