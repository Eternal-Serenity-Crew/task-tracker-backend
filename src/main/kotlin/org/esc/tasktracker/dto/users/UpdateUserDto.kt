package org.esc.tasktracker.dto.users

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

/**
 * Data transfer object for updating existing user information.
 *
 * Contains optional fields for modifying user profile data.
 * All fields are nullable, allowing partial updates where only
 * specified fields are modified. The [id] field is required to
 * identify which user to update.
 *
 * @param id The unique identifier of the user to update.
 *           Required field - must reference an existing user.
 * @param name Optional new name for the user.
 *             If provided, must be between 2 and 50 characters.
 *             If null, name remains unchanged.
 * @param email Optional new email address for the user.
 *              If provided, must be a valid email format.
 *              If null, email remains unchanged.
 *              Will be validated for uniqueness if changed.
 *
 * @see org.esc.tasktracker.controllers.UsersController.update
 * @see org.esc.tasktracker.services.UsersService.update
 * @see CreateUserDto
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
data class UpdateUserDto(
    val id: Long,
    @Size(min = 2, max = 50) val name: String?,
    @Email val email: String?,
)
