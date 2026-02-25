package org.esc.tasktracker.dto.jwt

import org.esc.tasktracker.entities.Users
import org.esc.tasktracker.interfaces.DtoClass
import java.util.UUID

/**
 * Data transfer object for creating JWT tokens.
 *
 * Contains the essential information needed to generate both access and refresh
 * JWT tokens. This DTO encapsulates the user identity and session context that
 * will be encoded into the token claims.
 *
 * @param user The user entity for whom the token is being generated.
 *             Contains user identification data (ID, email, roles) that will
 *             be embedded in the token claims for later authentication.
 * @param uuid Unique session identifier used to bind the token to a specific
 *             client session. Enables session management features like:
 *             - Multiple concurrent sessions per user
 *             - Session-specific token validation
 *             - Token revocation per session
 *
 * @see org.esc.tasktracker.security.JwtUtil.generateAccessToken
 * @see org.esc.tasktracker.security.JwtUtil.generateRefreshToken
 * @see org.esc.tasktracker.services.AuthService.generateTokens
 * @see Users
 * @see DtoClass
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
data class CreateJwtToken(
    val user: Users,
    val uuid: UUID,
) : DtoClass