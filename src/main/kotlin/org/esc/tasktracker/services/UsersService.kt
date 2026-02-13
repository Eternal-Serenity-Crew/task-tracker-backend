package org.esc.tasktracker.services

import org.esc.tasktracker.dto.filters.UsersFilterDto
import org.esc.tasktracker.dto.users.CreateUserDto
import org.esc.tasktracker.dto.users.UpdateUserDto
import org.esc.tasktracker.entities.Users
import org.esc.tasktracker.exceptions.DoubleRecordException
import org.esc.tasktracker.exceptions.NotFoundException
import org.esc.tasktracker.extensions.takeIfOrThrow
import org.esc.tasktracker.interfaces.CrudService
import org.esc.tasktracker.mappers.UsersMapper
import org.esc.tasktracker.repositories.UsersRepository
import org.esc.tasktracker.repositories.specs.UsersSpecifications
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UsersService(
    override val repository: UsersRepository,
    private val usersSpecifications: UsersSpecifications,
    private val usersMapper: UsersMapper,
    private val passwordEncoder: PasswordEncoder,
) : CrudService<Users, Long, CreateUserDto, UpdateUserDto, UsersFilterDto> {

    override fun getAll(
        filters: UsersFilterDto?,
        pageable: Pageable,
    ): Page<Users> {
        val specs = listOfNotNull(
            usersSpecifications.hasName(filters?.name),
            usersSpecifications.hasEmail(filters?.email)
        )

        return repository.findAll(Specification.allOf(specs), pageable)
    }

    fun getByEmail(email: String, throwable: Boolean = true): Users? = repository.findByEmail(email)
        ?: if (throwable) throw NotFoundException("Пользователя с email $email не существует.") else null

    @Transactional
    override fun create(item: CreateUserDto): Users {
        getByEmail(
            item.email,
            throwable = false
        )?.let { throw DoubleRecordException("Пользователь с таким email уже существует.") }

        return repository.save(usersMapper.userFromDto(item).copy(password = passwordEncoder.encode(item.password)!!))
    }

    @Transactional
    override fun update(item: UpdateUserDto): Users {
        val user = getById(item.id, message = "Пользователь с ID ${item.id} не найден.")!!

        item.name?.let { user.name = it }
        item.email?.takeIfOrThrow(
            predicate = { email -> getByEmail(email, false) == null },
            exception = { DoubleRecordException("Email ${item.email} занят") }
        )?.also { user.email = it }

        return repository.save(user)
    }

    @Transactional
    override fun deleteById(id: Long): String {
        getById(id, message = "Пользователь с ID $id не найден.")?.let {
            repository.deleteById(id)
        }

        return "Пользователь удален успешно."
    }

    @Transactional
    override fun deleteAll(): String {
        repository.deleteAll()

        return "Все пользователи удалены успещно."
    }
}