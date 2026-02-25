package org.esc.tasktracker.dto.users

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.esc.tasktracker.interfaces.DtoClass

/**
 * Data transfer object for user registration requests.
 *
 * Contains all required information for creating a new user account in the system.
 * This DTO includes validation constraints to ensure data integrity before
 * processing by the service layer.
 *
 * @param name User's full name or display name.
 *             Must be between 2 and 50 characters.
 *             Cannot be blank.
 * @param email User's email address used for authentication and communication.
 *              Must be a valid email format.
 *              Will be stored in lowercase and must be unique in the system.
 * @param password User's chosen password in plain text.
 *                 Must be at least 8 characters long (max 100).
 *                 Will be bcrypt-hashed before storage.
 *                 Never logged or exposed in responses.
 *
 * @see org.esc.tasktracker.controllers.AuthController.register
 * @see org.esc.tasktracker.services.AuthService.register
 * @see org.esc.tasktracker.services.UsersService.create
 * @see UpdateUserDto
 * @see DtoClass
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
data class CreateUserDto(
    @NotBlank @Size(min = 2, max = 50) val name: String,
    @Email val email: String,
    @NotBlank @Size(min = 8, max = 100) val password: String,
) : DtoClass
