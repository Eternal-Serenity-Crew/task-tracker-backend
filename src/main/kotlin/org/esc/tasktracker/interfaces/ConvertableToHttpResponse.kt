package org.esc.tasktracker.interfaces

import org.esc.tasktracker.io.BasicSuccessfulResponse

interface ConvertableToHttpResponse<T> {
    fun T.toHttpResponse(): BasicSuccessfulResponse<T> {
        return BasicSuccessfulResponse(this)
    }
}