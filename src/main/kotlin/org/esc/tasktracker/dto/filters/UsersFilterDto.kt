package org.esc.tasktracker.dto.filters

import org.esc.tasktracker.interfaces.FilterDtoClass

/**
 * Data transfer object for filtering user queries.
 *
 * Contains optional filter criteria for searching and paginating users.
 * All filters are applied using AND logic when multiple criteria are provided.
 * Used in conjunction with [org.esc.tasktracker.services.UsersService.getAll] for dynamic query building.
 *
 * @param name Optional filter by user's name. Typically, uses case-insensitive
 *             partial matching (SQL LIKE pattern) in repository implementations.
 *             If null, name filter is not applied.
 * @param email Optional filter by user email. Typically, uses exact matching
 *              for precision. If null, email filter is not applied.
 *
 * @see org.esc.tasktracker.controllers.UsersController.getAll
 * @see org.esc.tasktracker.services.UsersService.getAll
 * @see org.esc.tasktracker.repositories.specs.UsersSpecifications
 * @see FilterDtoClass
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
data class UsersFilterDto(
    val name: String?,
    val email: String?,
) : FilterDtoClass
