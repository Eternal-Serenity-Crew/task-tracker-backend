package org.esc.tasktracker.mappers

import org.esc.tasktracker.dto.users.CreateUserDto
import org.esc.tasktracker.entities.Users
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
fun interface UsersMapper {
    fun userFromDto(dto: CreateUserDto): Users
}