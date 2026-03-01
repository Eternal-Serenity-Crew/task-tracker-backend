package org.esc.tasktracker.mappers

import org.esc.tasktracker.dto.teams.CreateTeamDto
import org.esc.tasktracker.entities.Teams
import org.esc.tasktracker.entities.Users
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
fun interface TeamsMapper {

    @Mappings(
        Mapping(target = "owner", source = "user"),
        Mapping(target = "name", source = "dto.name"),
    )
    fun teamFromDto(dto: CreateTeamDto, user: Users): Teams
}