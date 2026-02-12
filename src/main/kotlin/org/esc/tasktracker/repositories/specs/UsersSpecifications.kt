package org.esc.tasktracker.repositories.specs

import org.esc.tasktracker.entities.Users
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component

@Component
class UsersSpecifications {
    fun hasName(name: String?): Specification<Users>? = name?.trim()
        ?.takeIf { it.isNotEmpty() }
        ?.let { name ->
            Specification<Users> { root, _, cb ->
                cb.like(cb.lower(root["name"]), "%${name.lowercase()}%")
            }
        }

    fun hasEmail(email: String?): Specification<Users>? = email?.trim()
        ?.takeIf { it.isNotEmpty() }
        ?.let { email ->
            Specification<Users> { root, _, cb ->
                cb.like(cb.lower(root["email"]), "%${email.lowercase()}%")
            }
        }
}