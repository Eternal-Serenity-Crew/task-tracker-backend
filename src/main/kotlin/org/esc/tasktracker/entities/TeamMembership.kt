package org.esc.tasktracker.entities

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import org.esc.tasktracker.enums.TeamRoles
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@Table(name = "team_membership")
@EntityListeners(AuditingEntityListener::class)
data class TeamMembership(
    @EmbeddedId
    val id: TeamMembershipId,

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: Users,

    @MapsId("teamId")
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