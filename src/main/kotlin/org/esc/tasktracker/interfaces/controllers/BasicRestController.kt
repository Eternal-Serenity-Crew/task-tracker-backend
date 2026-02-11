package org.esc.tasktracker.interfaces.controllers

import org.esc.tasktracker.interfaces.services.BasicApiService

interface BasicRestController {
    val service: BasicApiService<*, *>
}