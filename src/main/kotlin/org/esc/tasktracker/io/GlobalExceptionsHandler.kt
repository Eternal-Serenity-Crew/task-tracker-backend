package org.esc.tasktracker.io

import io.jsonwebtoken.security.SignatureException
import org.esc.tasktracker.exceptions.AbstractHttpException
import org.esc.tasktracker.exceptions.JwtAuthenticationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authorization.AuthorizationDeniedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionsHandler {

    /**
     * Basic exceptions handler
     * @return ResponseEntity with status 500 and exception message
     */
    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception): ResponseEntity<BasicErrorResponse> {
        val errorResponse = BasicErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "${ex::class}; ${ex.message}",
        )

        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    /**
     * Custom project exceptions handler
     */
    @ExceptionHandler(AbstractHttpException::class)
    fun handleNotFoundException(ex: AbstractHttpException): ResponseEntity<BasicErrorResponse> {
        val errorResponse = BasicErrorResponse(
            status = ex.status,
            message = ex.message?: "Exception occurred",
        )

        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    /**
     * Jwt exceptions handler
     */
    @ExceptionHandler(JwtAuthenticationException::class)
    fun handleJwtAuthenticationException(ex: JwtAuthenticationException): ResponseEntity<BasicErrorResponse> {
        val errorResponse = ex.message?.let {
            BasicErrorResponse(
                status = HttpStatus.UNAUTHORIZED.value(),
                message = it
            )
        }

        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(SignatureException::class)
    fun handleSignatureException(ex: SignatureException): ResponseEntity<BasicErrorResponse> {
        val errorResponse = ex.message?.let {
            BasicErrorResponse(
                status = HttpStatus.LOCKED.value(),
                message = "Jwt token verification failed. JWT validity cannot be asserted and should not be trusted."
            )
        }

        return ResponseEntity(errorResponse, HttpStatus.LOCKED)
    }

    @ExceptionHandler(AuthorizationDeniedException::class)
    fun handleAuthorizationDeniedException(ex: AuthorizationDeniedException): ResponseEntity<BasicErrorResponse> {
        val errorResponse = ex.message?.let {
            BasicErrorResponse(
                status = HttpStatus.FORBIDDEN.value(),
                message = "Access denied. You don't have permission to access this resource."
            )
        }

        return ResponseEntity(errorResponse, HttpStatus.FORBIDDEN)
    }
}