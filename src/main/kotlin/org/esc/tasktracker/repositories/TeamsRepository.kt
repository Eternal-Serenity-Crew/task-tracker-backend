package org.esc.tasktracker.repositories

import org.esc.tasktracker.entities.Teams
import org.esc.tasktracker.entities.Users
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface TeamsRepository : JpaRepository<Teams, Long>, JpaSpecificationExecutor<Teams> {
    fun findByOwner(owner: Users, pageable: Pageable): Page<Teams>
}