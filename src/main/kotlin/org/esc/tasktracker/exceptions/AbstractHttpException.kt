package org.esc.tasktracker.exceptions

/**
 * Abstract base class for all custom HTTP exceptions in the application.
 *
 * This class extends [RuntimeException] and provides a standardized structure
 * for exceptions that need to be translated into HTTP responses with specific
 * status codes. It serves as the foundation for the application's exception
 * hierarchy, enabling consistent error handling across all layers.
 *
 * @param status The HTTP status code associated with this exception.
 *               Will be used by [org.esc.tasktracker.io.GlobalExceptionsHandler] to determine
 *               the response status code.
 * @param message Optional error message describing the exception details.
 *                Will be included in the error response body.
 *
 * @see org.esc.tasktracker.io.GlobalExceptionsHandler.handleNotFoundException
 * @see RuntimeException
 * @see org.springframework.http.HttpStatus
 * @see org.esc.tasktracker.io.BasicErrorResponse
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
abstract class AbstractHttpException(val status: Int, override val message: String?) : RuntimeException(message)