package org.esc.tasktracker.interfaces

/**
 * Marker interface for filter DTO classes.
 *
 * Indicates that a class is used for filtering requests in getAll operations.
 * Provides type safety for controller and service generic parameters.
 *
 * @see CrudController
 * @see CrudService
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
interface FilterDtoClass