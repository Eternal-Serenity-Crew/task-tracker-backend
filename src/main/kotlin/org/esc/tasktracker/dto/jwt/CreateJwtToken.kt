package org.esc.tasktracker.dto.jwt

import org.esc.tasktracker.entities.Users
import org.esc.tasktracker.interfaces.DtoClass
import java.util.UUID

data class CreateJwtToken(
    val user: Users,
    val uuid: UUID,
) : DtoClass