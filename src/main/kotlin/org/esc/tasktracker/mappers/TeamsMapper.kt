package org.esc.tasktracker.mappers

import org.esc.tasktracker.dto.teams.CreateTeamDto
import org.esc.tasktracker.entities.TeamMembership
import org.esc.tasktracker.entities.Teams
import org.esc.tasktracker.entities.Users
import org.esc.tasktracker.enums.TeamRoles
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface TeamsMapper {

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "owner", source = "user"),
        Mapping(target = "name", source = "dto.name"),
    )
    fun teamFromDto(dto: CreateTeamDto, user: Users): Teams

    @Mappings(
        Mapping(target = "user", source = "user"),
        Mapping(target = "team", source = "team"),
        Mapping(target = "role", source = "role"),
        Mapping(target = "id", ignore = true),
        Mapping(target = "createdAt", ignore = true),
        Mapping(target = "updatedAt", ignore = true),
    )
    fun teamMembershipFromDto(user: Users, team: Teams, role: TeamRoles): TeamMembership
}