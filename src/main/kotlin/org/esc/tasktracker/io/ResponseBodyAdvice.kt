package org.esc.tasktracker.io

import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

/**
 * Global REST controller advice that intercepts and standardizes all API responses.
 *
 * This component implements [ResponseBodyAdvice] to provide consistent response formatting
 * across all REST endpoints. It automatically wraps controller responses into standardized
 * [BasicSuccessfulResponse] or [BasicErrorResponse] objects, ensuring a uniform API response
 * structure throughout the application.
 *
 * @see ResponseBodyAdvice
 * @see BasicSuccessfulResponse
 * @see BasicErrorResponse
 * @see RestControllerAdvice
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
@RestControllerAdvice
class ResponseBodyAdvice : ResponseBodyAdvice<Any> {

    /**
     * Determines whether this advice should be applied to the response.
     *
     * This implementation always returns true, meaning the advice is applied to
     * all controller responses regardless of return type or converter.
     *
     * @param returnType Metadata about the method return type
     * @param converterType The HTTP message converter type being used
     * @return Always true to apply advice to all responses
     */
    override fun supports(
        returnType: MethodParameter,
        converterType: Class<out HttpMessageConverter<*>>
    ) = true

    /**
     * Intercepts and potentially modifies the response body before it is written.
     *
     * This method applies the response wrapping logic:
     * 1. **Null handling**: Converts null responses to `BasicSuccessfulResponse(null)`
     * 2. **Error response preservation**: Passes through [BasicErrorResponse] unchanged
     * 3. **Success response preservation**: Passes through [BasicSuccessfulResponse] unchanged
     *    (prevents double-wrapping)
     * 4. **Default wrapping**: Wraps any other object in a [BasicSuccessfulResponse]
     *
     * This ensures that all API responses follow a consistent format while respecting
     * explicitly returned response types.
     *
     * @param body The response body to be written (can be null)
     * @param returnType Metadata about the method return type
     * @param selectedContentType The content type selected for the response
     * @param selectedConverterType The HTTP message converter type being used
     * @param request The current HTTP request
     * @param response The current HTTP response
     * @return The modified (or original) response body to be written
     */
    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        return when (body) {
            null -> BasicSuccessfulResponse(null)
            is BasicErrorResponse -> body
            is BasicSuccessfulResponse<*> -> body
            else -> BasicSuccessfulResponse(body)
        }
    }
}