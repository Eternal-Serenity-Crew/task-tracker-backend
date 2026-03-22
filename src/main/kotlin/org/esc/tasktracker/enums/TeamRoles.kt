package org.esc.tasktracker.enums

/**
 * Defines roles/permissions for team members.
 * Used in conjunction with [org.esc.tasktracker.entities.TeamMembership] to control access within teams.
 *
 * ## Available Roles:
 * - **[MEMBER]**: Standard team member with view/edit permissions on team tasks
 * - **[ADMIN]**: Team administrator with member management and team configuration permissions
 *
 * ## Storage:
 * Stored as `VARCHAR(255)` via `@Enumerated(EnumType.STRING)` in `team_membership.role` column.
 *
 * @see org.esc.tasktracker.entities.TeamMembership
 *
 * @author Vladimir Fokin
 * @since 0.3.0
 */
enum class TeamRoles {
    MEMBER,
    ADMIN,
}