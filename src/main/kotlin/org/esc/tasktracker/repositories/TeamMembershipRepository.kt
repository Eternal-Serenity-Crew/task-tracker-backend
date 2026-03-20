package org.esc.tasktracker.repositories

import org.esc.tasktracker.entities.TeamMembership
import org.esc.tasktracker.entities.Teams
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface TeamMembershipRepository : JpaRepository<TeamMembership, Long>, JpaSpecificationExecutor<TeamMembership> {
    fun findByUserIdAndTeamId(userId: Long, teamId: Long): TeamMembership?
    fun deleteAllByTeam(team: Teams)
}