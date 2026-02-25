package org.esc.tasktracker.controllers

import jakarta.validation.Valid
import org.esc.tasktracker.dto.filters.UsersFilterDto
import org.esc.tasktracker.dto.users.CreateUserDto
import org.esc.tasktracker.dto.users.UpdateUserDto
import org.esc.tasktracker.entities.Users
import org.esc.tasktracker.extensions.toHttpResponse
import org.esc.tasktracker.interfaces.CrudController
import org.esc.tasktracker.services.UsersService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

/**
 * REST controller for managing user resources.
 *
 * This controller provides HTTP endpoints for CRUD operations on users, implementing
 * [CrudController] interface for standard operations and adding user-specific endpoints
 * like email-based lookup. All endpoints are mapped under the "/api/v1/users" base path.
 *
 * ## Endpoints Overview:
 * - **GET /api/v1/users** - Get paginated list of users with optional filters
 * - **GET /api/v1/users/{id}** - Get a specific user by ID
 * - **GET /api/v1/users/getByEmail/{email}** - Get a user by email address
 * - **POST /api/v1/users** - Create a new user
 * - **PATCH /api/v1/users** - Update an existing user
 * - **DELETE /api/v1/users** - Delete all users
 * - **DELETE /api/v1/users/{id}** - Delete a specific user by ID
 *
 * ## Validation:
 * Request bodies are validated using [@Valid] annotations, ensuring that
 * incoming data meets the constraints defined in the DTOs.
 *
 * @param usersService Service layer for user operations
 *
 * @see RestController
 * @see RequestMapping
 * @see CrudController
 * @see UsersService
 * @see Users
 * @see CreateUserDto
 * @see UpdateUserDto
 * @see UsersFilterDto
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/users")
class UsersController(private val usersService: UsersService) :
    CrudController<Users, Long, CreateUserDto, UpdateUserDto, UsersFilterDto> {

    @GetMapping
    override fun getAll(
        filters: UsersFilterDto,
        pageable: Pageable
    ): Page<Users> = usersService.getAll(filters, pageable)

    @GetMapping("/{id}")
    override fun getById(@PathVariable id: Long): Users =
        usersService.getById(id, throwable = true, message = "Пользователь с ID $id не найден.")!!

    @GetMapping("/getByEmail/{email}")
    fun getByEmail(@PathVariable email: String): Users = usersService.getByEmail(email)!!

    @PostMapping
    override fun create(@Valid @RequestBody item: CreateUserDto): Users = usersService.create(item)

    @PatchMapping
    override fun update(@Valid @RequestBody item: UpdateUserDto): Users = usersService.update(item)

    @DeleteMapping
    override fun deleteAll() = usersService.deleteAll().toHttpResponse()

    @DeleteMapping("/{id}")
    override fun deleteById(@PathVariable id: Long) = usersService.deleteById(id).toHttpResponse()
}