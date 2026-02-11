package org.esc.tasktracker.interfaces.controllers.crud

import org.esc.tasktracker.interfaces.controllers.BasicRestController
import org.esc.tasktracker.interfaces.services.crud.ICreateService


interface ICreateController<T, ID, CrDTO> : BasicRestController {
    override val service: ICreateService<T, ID, CrDTO>

    fun create(item: CrDTO): Any
}