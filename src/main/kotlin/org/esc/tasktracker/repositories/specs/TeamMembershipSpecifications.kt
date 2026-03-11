package org.esc.tasktracker.repositories.specs

import jakarta.persistence.criteria.Path
import org.esc.tasktracker.entities.TeamMembership
import org.esc.tasktracker.entities.Teams
import org.esc.tasktracker.entities.Users
import org.esc.tasktracker.enums.TeamRoles
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component

@Component
class TeamMembershipSpecifications {
    fun hasUserId(userId: Long?): Specification<TeamMembership>? = userId?.let { userId ->
        Specification<TeamMembership> { root, _, cb ->
            val userPath: Path<Users> = root["user"]
            val userIdPath: Path<Long> = userPath["id"]

            cb.equal(userIdPath, userId)
        }
    }

    fun hasTeamId(teamId: Long?): Specification<TeamMembership>? = teamId?.let { teamId ->
        Specification<TeamMembership> { root, _, cb ->
            val teamPath: Path<Teams> = root["team"]
            val teamIdPath: Path<Long> = teamPath["id"]

            cb.equal(teamIdPath, teamId)
        }
    }

    fun hasTeamRole(role: TeamRoles?): Specification<TeamMembership>? = role?.let { role ->
        Specification<TeamMembership> { root, _, cb ->
            val rolePath: Path<TeamRoles> = root["role"]

            cb.equal(rolePath, role)
        }
    }
}