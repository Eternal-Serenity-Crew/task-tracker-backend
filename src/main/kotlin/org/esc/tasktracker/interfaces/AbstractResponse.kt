package org.esc.tasktracker.interfaces

/**
 * Base interface for all API responses.
 *
 * Defines the common structure for all responses returned by the API,
 * ensuring consistency across success and error responses.
 *
 * @param T The type of the message payload.
 * @property status HTTP status code of the response.
 * @property message The response payload data. Can be null for empty responses.
 *
 * @see org.esc.tasktracker.io.BasicSuccessfulResponse
 * @see org.esc.tasktracker.io.BasicErrorResponse
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
interface AbstractResponse<T> {
    val status: Int
    val message: T?
}