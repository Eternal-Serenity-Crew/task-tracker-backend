package org.esc.tasktracker.controllers

import jakarta.validation.Valid
import org.esc.tasktracker.dto.auth.LoginUserDto
import org.esc.tasktracker.dto.auth.UpdateUserTokensDto
import org.esc.tasktracker.dto.users.CreateUserDto
import org.esc.tasktracker.services.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

/**
 * REST controller for authentication operations.
 *
 * This controller provides endpoints for user registration, login, and token refresh operations.
 * All authentication endpoints are mapped under the "/api/v1/auth" base path and work with
 * JWT tokens for stateless authentication.
 *
 * ## Endpoints Overview:
 * - **POST /api/v1/auth/register** - Register a new user account
 * - **POST /api/v1/auth/login** - Authenticate existing user and receive tokens
 * - **POST /api/v1/auth/updateTokens** - Refresh access token using refresh token
 *
 * ## Authentication Flow:
 * 1. **Registration**: New users register with email/password → receive access + refresh tokens
 * 2. **Login**: Existing users authenticate → receive new access + refresh tokens
 * 3. **Token Refresh**: When access token expires, use refresh token → get new token pair
 *
 * ## Session Management:
 * Each request requires a [Session-ID] header that uniquely identifies the client session.
 * This ID is used to track and manage refresh tokens, enabling features like:
 * - Multiple sessions per user
 * - Session invalidation
 * - Token revocation
 *
 * @param authService Service layer for authentication operations
 *
 * @see RestController
 * @see RequestMapping
 * @see AuthService
 * @see CreateUserDto
 * @see LoginUserDto
 * @see UpdateUserTokensDto
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/register")
    fun register(
        @Valid @RequestBody item: CreateUserDto,
        @RequestHeader("Session-ID") sessionId: UUID
    ) = authService.register(item, sessionId)

    @PostMapping("/login")
    fun login(
        @RequestBody item: LoginUserDto,
        @RequestHeader("Session-ID") sessionId: UUID
    ) = authService.login(item, sessionId)

    @PostMapping("/updateTokens")
    fun updateTokens(
        @RequestBody item: UpdateUserTokensDto,
        @RequestHeader("Session-ID") sessionId: UUID
    ) = authService.updateTokens(item, sessionId)
}