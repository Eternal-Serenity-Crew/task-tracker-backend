package org.esc.tasktracker.io.filters

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.esc.tasktracker.security.JwtUtil
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.naming.AuthenticationException
import kotlin.text.startsWith
import kotlin.text.substring

/**
 * Servlet filter that intercepts HTTP requests to validate JWT tokens and set up authentication
 * in the Spring Security context.
 *
 * This filter extends [OncePerRequestFilter] to guarantee a single execution per request.
 * It extracts JWT tokens from the Authorization header, validates them, and if valid,
 * creates an authentication token in the security context for the current request.
 *
 * The filter processes requests as follows:
 * 1. Extracts the Authorization header from the HTTP request
 * 2. Checks if the header exists and starts with "Bearer " prefix
 * 3. Extracts the JWT token by removing the prefix
 * 4. Validates the token using [JwtUtil]
 * 5. If valid, retrieves the user information and creates an authentication token
 * 6. Sets the authentication in Spring Security's [SecurityContextHolder]
 * 7. Proceeds with the filter chain
 *
 * If authentication fails (invalid token, expired token, etc.), the security context is cleared
 * and an [AuthenticationException] is thrown.
 *
 * @param jwtUtil Utility component for JWT token operations including validation and user extraction
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
@Component
class JwtRequestFilter(
    private val jwtUtil: JwtUtil
) : OncePerRequestFilter() {

    /**
     * Processes each HTTP request to validate JWT tokens and set up authentication.
     *
     * This method is called once per request and performs the following steps:
     * - Retrieves the Authorization header from the request
     * - Validates the presence of a Bearer token
     * - Verifies the JWT token's validity using [JwtUtil.verifyToken]
     * - If valid, extracts user information and creates an authentication token
     * - Sets the authentication in the security context if not already present
     * - Continues the filter chain processing
     *
     * The filter is designed to be non-blocking and will always proceed with the filter chain,
     * even when no valid token is present (anonymous access). Authentication failures
     * result in cleared security context and exception propagation.
     *
     * @param request The HTTP servlet request
     * @param response The HTTP servlet response
     * @param filterChain The filter chain for continuing request processing
     * @throws AuthenticationException If token validation fails (expired, malformed, or invalid signature)
     *
     * @see OncePerRequestFilter
     * @see JwtUtil
     * @see SecurityContextHolder
     * @see UsernamePasswordAuthenticationToken
     */
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val authorizationHeader = request.getHeader("Authorization")

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                val jwtToken = authorizationHeader.substring(7)

                if (jwtUtil.verifyToken(jwtToken)) {
                    val user = jwtUtil.getUserFromToken(jwtToken)
                    val authorities = emptyList<GrantedAuthority>()

                    if (SecurityContextHolder.getContext().authentication == null) {
                        val authReq = UsernamePasswordAuthenticationToken(user!!, null, authorities)
                        SecurityContextHolder.getContext().authentication = authReq
                    }
                }
            }

            filterChain.doFilter(request, response)
        } catch (ex: AuthenticationException) {
            SecurityContextHolder.clearContext()
            throw ex
        }
    }
}