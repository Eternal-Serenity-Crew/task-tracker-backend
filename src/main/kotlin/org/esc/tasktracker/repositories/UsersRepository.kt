package org.esc.tasktracker.repositories

import org.esc.tasktracker.entities.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {
    fun findByEmail(email: String): Users?
}