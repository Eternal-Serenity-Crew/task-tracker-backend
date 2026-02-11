package org.esc.tasktracker.interfaces.services.crud

import org.esc.tasktracker.interfaces.services.BasicApiService

interface ICreateService<T, ID, CrDTO> : BasicApiService<T, ID> {
    fun create(item: CrDTO): Any
}