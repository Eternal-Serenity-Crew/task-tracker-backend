package org.esc.tasktracker.entities

import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.esc.tasktracker.enums.TeamRoles
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

/**
 * Represents a user's membership in a team within the task tracker system.
 * Models the many-to-many relationship between [Users] and [Teams] with additional
 * role and audit information.
 *
 * ## Key Concepts:
 * - **Surrogate Primary Key**: `id` (auto-generated) for technical identification
 * - **Business Uniqueness**: `(user_id, team_id)` UNIQUE constraint ensures one membership per user-team pair
 * - **Roles**: Defines permissions within team ([TeamRoles.MEMBER], [TeamRoles.ADMIN])
 * - **Auditing**: Automatic timestamps via [AuditingEntityListener]
 *
 * ## Relationships:
 * - **Many-to-One with [Users]**: Multiple memberships per user
 * - **Many-to-One with [Teams]**: Multiple members per team
 *
 * ## Entity Listeners:
 * - **[AuditingEntityListener]**: Manages [createdAt] and [updatedAt] timestamps
 *
 * @param id Auto-increment primary key of the membership record
 * @param user The user who is a member of the team. Lazy-loaded.
 * @param team The team the user belongs to. Lazy-loaded.
 * @param role The role/permissions of the user within the team
 * @param createdAt When the membership was established
 * @param updatedAt When the membership/role was last modified
 *
 * @see Users
 * @see Teams
 * @see TeamRoles
 * @see AuditingEntityListener
 * @see CreatedDate
 * @see LastModifiedDate
 *
 * @author Vladimir Fokin
 * @since 0.3.0
 */
@Entity
@Table(name = "team_membership")
@EntityListeners(AuditingEntityListener::class)
data class TeamMembership(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: Users,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    val team: Teams,

    @Enumerated(EnumType.STRING)
    val role: TeamRoles = TeamRoles.MEMBER,

    @CreatedDate
    var createdAt: Instant?,

    @LastModifiedDate
    var updatedAt: Instant?,
)