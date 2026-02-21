package org.esc.tasktracker.unit.services

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.esc.tasktracker.exceptions.DoubleRecordException
import org.esc.tasktracker.security.JwtUtil
import org.esc.tasktracker.services.AuthService
import org.esc.tasktracker.services.UsersService
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
            val user = createUser()
            val uuid = UUID.randomUUID()

            every { usersService.create(createUserDto) } throws DoubleRecordException("Пользователь с email уже существует.")

            assertThrows<DoubleRecordException> { service.register(createUserDto, uuid) }

            verify { usersService.create(createUserDto) }
            verify(exactly = 0) { jwtUtil.removeOldRefreshTokenByUUID(uuid) }
            verify(exactly = 0) { jwtUtil.generateAccessToken(any()) }
            verify(exactly = 0) { jwtUtil.generateRefreshToken(any()) }
        }
    }
}