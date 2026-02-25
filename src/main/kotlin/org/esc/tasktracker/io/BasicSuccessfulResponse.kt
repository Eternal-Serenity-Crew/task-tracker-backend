package org.esc.tasktracker.io

import kotlinx.serialization.Serializable
import org.esc.tasktracker.interfaces.AbstractResponse
import org.springframework.http.HttpStatus

/**
 * A basic successful response data class used for standardized success responses across the API.
 *
 * This generic response type is used when a request completes successfully. It provides
 * a consistent success format with:
 * - HTTP status code indicating the success type (defaults to 200 OK)
 * - A typed data payload containing the actual response data
 *
 * The response is serializable to JSON/other formats through Kotlinx.Serialization.
 *
 * @param T The type of data contained in the successful response
 * @param message The actual response data payload of type T
 * @param status The HTTP status code for the success response (defaults to HttpStatus.OK.value() = 200)
 * @param <T> The type parameter for the response data
 *
 * @see AbstractResponse
 * @see BasicErrorResponse
 * @see HttpStatus
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
@Serializable
data class BasicSuccessfulResponse<T>(
    override val message: T,
    override val status: Int = HttpStatus.OK.value(),
) : AbstractResponse<T>