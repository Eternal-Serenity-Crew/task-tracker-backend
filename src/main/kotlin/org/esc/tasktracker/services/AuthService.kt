package org.esc.tasktracker.services

import org.esc.tasktracker.dto.auth.LoginUserDto
import org.esc.tasktracker.dto.auth.UpdateUserTokensDto
import org.esc.tasktracker.dto.jwt.CreateJwtToken
import org.esc.tasktracker.dto.jwt.UserTokensDto
import org.esc.tasktracker.dto.users.CreateUserDto
import org.esc.tasktracker.entities.Users
import org.esc.tasktracker.exceptions.JwtAuthenticationException
import org.esc.tasktracker.security.JwtUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AuthService(
    private val usersService: UsersService,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: PasswordEncoder,
) {

    fun register(item: CreateUserDto, sessionId: UUID): UserTokensDto {
        return generateTokens(usersService.create(item), sessionId)
    }

    fun login(item: LoginUserDto, sessionId: UUID): UserTokensDto {
        return usersService.getByEmail(item.email, throwable = false)?.let { user ->
            if (!passwordEncoder.matches(item.password, user.password)) {
                throw JwtAuthenticationException("Неверные логин или пароль.")
            }
            generateTokens(user, sessionId)
        } ?: throw JwtAuthenticationException("Неверные логин или пароль.")
    }

    @Transactional
    fun updateTokens(data: UpdateUserTokensDto, sessionId: UUID): UserTokensDto {
        jwtUtil.verifyToken(data.accessToken, throwTimeLimit = false)
        jwtUtil.verifyToken(data.refreshToken, sessionId)

        return jwtUtil.getUserFromToken(data.refreshToken)
            ?.let { user -> generateTokens(user, sessionId) }
            ?: throw JwtAuthenticationException("Неверный refresh-токен.")
    }

    private fun generateTokens(user: Users, sessionId: UUID): UserTokensDto {
        jwtUtil.removeOldRefreshTokenByUUID(sessionId)
        return UserTokensDto(
            accessToken = jwtUtil.generateAccessToken(CreateJwtToken(user, sessionId)),
            refreshToken = jwtUtil.generateRefreshToken(CreateJwtToken(user, sessionId))
        )
    }
}