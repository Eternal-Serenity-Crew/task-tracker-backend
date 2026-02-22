package org.esc.tasktracker.io

import kotlinx.serialization.Serializable
import org.esc.tasktracker.interfaces.AbstractResponse

/**
 * A basic error response data class used for standardized error responses across the API.
 *
 * This response type is used when an error occurs during request processing. It provides
 * a consistent error format with HTTP status code and error message, without any data payload.
 *
 * @param status The HTTP status code indicating the error type (e.g., 400 for Bad Request,
 *               401 for Unauthorized, 404 for Not Found, 500 for Internal Server Error)
 * @param message A human-readable error message describing what went wrong
 *
 * @see AbstractResponse
 * @see BasicSuccessfulResponse
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
@Serializable
data class BasicErrorResponse(override val status: Int, override val message: String) : AbstractResponse<String>
