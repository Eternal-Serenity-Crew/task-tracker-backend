package org.esc.tasktracker.interfaces

import org.esc.tasktracker.io.BasicSuccessfulResponse

/**
 * Interface providing HTTP response conversion capabilities.
 *
 * Enables objects to be converted into standardized HTTP responses.
 * Primarily used by DTOs to provide consistent response formatting.
 *
 * @param T The type of object being converted.
 *
 * @see BasicSuccessfulResponse
 * @see DtoClass
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
interface ConvertableToHttpResponse<T> {
    fun T.toHttpResponse(): BasicSuccessfulResponse<T> {
        return BasicSuccessfulResponse(this)
    }
}