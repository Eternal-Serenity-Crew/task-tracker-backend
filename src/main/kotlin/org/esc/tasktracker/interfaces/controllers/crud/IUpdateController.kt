package org.esc.tasktracker.interfaces.controllers.crud

import org.esc.tasktracker.interfaces.controllers.BasicRestController
import org.esc.tasktracker.interfaces.services.crud.IUpdateService

interface IUpdateController<T, ID, UpDTO> : BasicRestController {
    override val service: IUpdateService<T, ID, UpDTO>

    fun update(item: UpDTO): Any
}