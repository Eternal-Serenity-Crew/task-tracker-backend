package org.esc.tasktracker.unit.services

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.esc.tasktracker.exceptions.DoubleRecordException
import org.esc.tasktracker.exceptions.JwtAuthenticationException
import org.esc.tasktracker.security.JwtUtil
import org.esc.tasktracker.services.AuthService
import org.esc.tasktracker.services.UsersService
import org.esc.tasktracker.unit.generators.createLoginUserDto
import org.esc.tasktracker.unit.generators.createUser
import org.esc.tasktracker.unit.generators.createUserCreateDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.assertThrows
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*
import kotlin.test.Test

class AuthServiceTest {

    private var usersService = mockk<UsersService>()
    private var jwtUtil = mockk<JwtUtil>()
    private var passwordEncoder = mockk<PasswordEncoder>()
    private var service = AuthService(usersService, jwtUtil, passwordEncoder)

    @Nested
    inner class RegisterTests {

        @Test
        fun `should register user and return tokens`() {
            val createUserDto = createUserCreateDto()
            val user = createUser()
            val uuid = UUID.randomUUID()

            every { usersService.create(createUserDto) } returns user
            every { jwtUtil.removeOldRefreshTokenByUUID(uuid) } just runs
            every { jwtUtil.generateAccessToken(any()) } returns "accessToken"
            every { jwtUtil.generateRefreshToken(any()) } returns "refreshToken"

            val result = service.register(createUserDto, uuid)

            assertEquals("accessToken", result.accessToken)
            assertEquals("refreshToken", result.refreshToken)

            verify { usersService.create(createUserDto) }
            verify { jwtUtil.removeOldRefreshTokenByUUID(uuid) }
            verify { jwtUtil.generateAccessToken(any()) }
            verify { jwtUtil.generateRefreshToken(any()) }
        }

        @Test
        fun `should throw exception when user already exists`() {
            val createUserDto = createUserCreateDto()
            val uuid = UUID.randomUUID()

            every { usersService.create(createUserDto) } throws DoubleRecordException("Пользователь с email уже существует.")

            assertThrows<DoubleRecordException> { service.register(createUserDto, uuid) }

            verify { usersService.create(createUserDto) }
            verify(exactly = 0) { jwtUtil.removeOldRefreshTokenByUUID(uuid) }
            verify(exactly = 0) { jwtUtil.generateAccessToken(any()) }
            verify(exactly = 0) { jwtUtil.generateRefreshToken(any()) }
        }
    }

    @Nested
    inner class LoginTests {

        @Test
        fun `should login user and return tokens`() {
            val user = createUser()
            val uuid = UUID.randomUUID()
            val loginDto = createLoginUserDto(user.email, user.password)

            every { usersService.getByEmail(any(), any()) } returns user
            every { passwordEncoder.matches(any(), any()) } returns true
            every { jwtUtil.removeOldRefreshTokenByUUID(uuid) } just runs
            every { jwtUtil.generateAccessToken(any()) } returns "accessToken"
            every { jwtUtil.generateRefreshToken(any()) } returns "refreshToken"

            val result = service.login(loginDto, uuid)

            assertEquals("accessToken", result.accessToken)
            assertEquals("refreshToken", result.refreshToken)

            verify { usersService.getByEmail(user.email, false) }
            verify { jwtUtil.removeOldRefreshTokenByUUID(uuid) }
            verify { jwtUtil.generateAccessToken(any()) }
            verify { jwtUtil.generateRefreshToken(any()) }
        }

        @Test
        fun `should throw exception when user's password is invalid`() {
            val user = createUser()
            val uuid = UUID.randomUUID()
            val loginDto = createLoginUserDto(user.email, "wrong_password")

            every { usersService.getByEmail(any(), any()) } returns user
            every { passwordEncoder.matches(any(), any()) } returns false

            val exception = assertThrows<JwtAuthenticationException> { service.login(loginDto, uuid) }

            assertEquals(exception.message, "Неверные логин или пароль.")

            verify { usersService.getByEmail(user.email, false) }
            verify { passwordEncoder.matches(loginDto.password, user.password) }
            verify(exactly = 0) { jwtUtil.removeOldRefreshTokenByUUID(uuid) }
            verify(exactly = 0) { jwtUtil.generateAccessToken(any()) }
            verify(exactly = 0) { jwtUtil.generateRefreshToken(any()) }
        }

        @Test
        fun `should throw exception when user doesn't exists`() {
            val user = createUser()
            val uuid = UUID.randomUUID()
            val loginDto = createLoginUserDto("wrong@mail.ru", user.password)

            every { usersService.getByEmail(any(), any()) } returns null

            val exception = assertThrows<JwtAuthenticationException> { service.login(loginDto, uuid) }

            assertEquals(exception.message, "Неверные логин или пароль.")

            verify { usersService.getByEmail(loginDto.email, false) }
            verify(exactly = 0) { passwordEncoder.matches(user.password, user.password) }
            verify(exactly = 0) { jwtUtil.removeOldRefreshTokenByUUID(uuid) }
            verify(exactly = 0) { jwtUtil.generateAccessToken(any()) }
            verify(exactly = 0) { jwtUtil.generateRefreshToken(any()) }
        }
    }
}