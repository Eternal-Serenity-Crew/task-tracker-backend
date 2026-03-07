package org.esc.tasktracker.repositories.specs

import jakarta.persistence.criteria.Path
import org.esc.tasktracker.entities.Teams
import org.esc.tasktracker.entities.Users
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component

@Component
class TeamsSpecifications {

    fun hasName(name: String?): Specification<Teams>? = name?.trim()
        ?.takeIf { it.isNotEmpty() }
        ?.let { name ->
            Specification<Teams> { root, _, cb ->
                cb.like(cb.lower(root["name"]), "%${name.lowercase()}%")
            }
        }

    fun hasOwnerId(id: Long?): Specification<Teams>? = id
        ?.let { id ->
            Specification<Teams> { root, _, cb ->
                val user: Path<Users> = root["owner"]
                val userId: Path<Long> = user["id"]

                cb.equal(userId, id)
            }
        }
}
