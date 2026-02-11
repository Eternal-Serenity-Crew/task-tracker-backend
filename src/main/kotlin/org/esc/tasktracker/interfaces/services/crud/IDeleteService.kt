package org.esc.tasktracker.interfaces.services.crud

import org.esc.tasktracker.interfaces.services.BasicApiService


interface IDeleteService<T, ID> : BasicApiService<T, ID> {
    fun deleteById(id: ID): Any?
    fun deleteAll(): Any? = repository.deleteAll()
}