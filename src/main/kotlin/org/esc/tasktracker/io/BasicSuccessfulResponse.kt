package org.esc.tasktracker.io

import kotlinx.serialization.Serializable
import org.esc.tasktracker.interfaces.AbstractResponse
import org.springframework.http.HttpStatus

@Serializable
data class BasicSuccessfulResponse<T>(
    override val message: T,
    override val status: Int = HttpStatus.OK.value(),
) : AbstractResponse<T>