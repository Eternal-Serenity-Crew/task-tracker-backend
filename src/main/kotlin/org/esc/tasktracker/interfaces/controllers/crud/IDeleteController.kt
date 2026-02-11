package org.esc.tasktracker.interfaces.controllers.crud

import org.esc.tasktracker.interfaces.controllers.BasicRestController
import org.esc.tasktracker.interfaces.services.crud.IDeleteService

interface IDeleteController<T, ID> : BasicRestController {
    override val service : IDeleteService<T, ID>

    fun deleteAll(): Any
    fun deleteById(id : ID): Any
}