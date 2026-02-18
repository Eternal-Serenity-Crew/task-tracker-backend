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