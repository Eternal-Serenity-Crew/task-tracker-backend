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