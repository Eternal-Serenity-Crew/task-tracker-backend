package org.esc.tasktracker.exceptions

import javax.naming.AuthenticationException

class JwtAuthenticationException(message: String) : AuthenticationException(message)