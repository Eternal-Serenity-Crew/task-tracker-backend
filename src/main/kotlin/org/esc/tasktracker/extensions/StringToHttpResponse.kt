package org.esc.tasktracker.extensions

import org.esc.tasktracker.io.BasicSuccessfulResponse

fun String.toHttpResponse(): BasicSuccessfulResponse<String> = BasicSuccessfulResponse(this)