package org.esc.tasktracker.io

import kotlinx.serialization.Serializable
import org.esc.tasktracker.interfaces.AbstractResponse

@Serializable
data class BasicErrorResponse(override val status: Int, override val message: String) : AbstractResponse<String>
