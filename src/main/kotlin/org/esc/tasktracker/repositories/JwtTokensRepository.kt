package org.esc.tasktracker.repositories

import org.esc.tasktracker.entities.JwtTokensStorage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface JwtTokensRepository : JpaRepository<JwtTokensStorage, Long> {
    fun findByUuid(uuid: UUID): JwtTokensStorage?
    fun deleteByToken(token: String)
    fun deleteByUuid(uuid: UUID)
}