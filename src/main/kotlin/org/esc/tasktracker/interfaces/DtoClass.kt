package org.esc.tasktracker.interfaces

/**
 * Marker interface for DTO classes.
 *
 * Indicates that a class is a Data Transfer Object and provides
 * HTTP response conversion capabilities through [ConvertableToHttpResponse].
 *
 * @see ConvertableToHttpResponse
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
interface DtoClass : ConvertableToHttpResponse<DtoClass>