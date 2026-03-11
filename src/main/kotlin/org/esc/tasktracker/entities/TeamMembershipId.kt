package org.esc.tasktracker.entities

import jakarta.persistence.Embeddable
import java.io.Serializable

/**
 * Composite primary key for [TeamMembership] entity.
 * Represents unique combination of user and team membership.
 *
 * @property userId The ID of the [Users] entity. Maps to `user_id` column.
 * @property teamId The ID of the [Teams] entity. Maps to `team_id` column.
 *
 * ## Requirements:
 * - Must implement [Serializable] for Hibernate session and L2 cache
 * - Must override [equals] and [hashCode] for proper entity identification
 * - Fields must match the `@Id` properties referenced by `@IdClass` or `@MapsId`
 *
 * @see TeamMembership
 * @see Serializable
 *
 * @author Vladimir Fokin
 * @since 0.3.0
 */
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