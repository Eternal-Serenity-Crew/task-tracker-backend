package org.esc.tasktracker.entities

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class TeamMembershipId(
    val userId: Long,
    val teamId: Long
) : Serializable {
    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other !is TeamMembershipId -> false
        else -> userId == other.userId && teamId == other.teamId
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 52 * result + teamId.hashCode()
        return result
    }

}