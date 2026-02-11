package org.esc.tasktracker.interfaces.controllers

import org.esc.tasktracker.interfaces.FilterDtoClass
import org.esc.tasktracker.interfaces.controllers.crud.ICreateController
import org.esc.tasktracker.interfaces.controllers.crud.IDeleteController
import org.esc.tasktracker.interfaces.controllers.crud.IReadController
import org.esc.tasktracker.interfaces.controllers.crud.IUpdateController
import org.esc.tasktracker.interfaces.services.CrudService

interface CrudController<T, ID, CrDTO, UpDTO, Filters : FilterDtoClass>
    : IReadController<T, ID, Filters>,
    ICreateController<T, ID, CrDTO>,
    IUpdateController<T, ID, UpDTO>,
    IDeleteController<T, ID> {
    override val service: CrudService<T, ID, CrDTO, UpDTO, Filters>
}