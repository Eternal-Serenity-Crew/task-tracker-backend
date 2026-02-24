package org.esc.tasktracker.extensions

import org.esc.tasktracker.io.BasicSuccessfulResponse

/**
 * Converts a string message into a standardized successful HTTP response.
 *
 * Extension function that wraps a string result into a [BasicSuccessfulResponse]
 * for consistent API response formatting. Useful for returning simple success
 * messages from controller endpoints.
 *
 * @return [BasicSuccessfulResponse] containing this string as the message payload.
 *
 * @see BasicSuccessfulResponse
 * @see org.esc.tasktracker.interfaces.CrudController.deleteById
 * @see org.esc.tasktracker.interfaces.CrudController.deleteAll
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
fun String.toHttpResponse(): BasicSuccessfulResponse<String> = BasicSuccessfulResponse(this)