package org.esc.tasktracker.interfaces.services.crud

import org.esc.tasktracker.interfaces.services.BasicApiService

interface IUpdateService<T, ID, UpDTO> : BasicApiService<T, ID> {
    fun update(item: UpDTO): Any
}