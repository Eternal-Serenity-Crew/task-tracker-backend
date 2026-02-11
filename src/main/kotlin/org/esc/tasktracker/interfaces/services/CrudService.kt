package org.esc.tasktracker.interfaces.services

import org.esc.tasktracker.interfaces.FilterDtoClass
import org.esc.tasktracker.interfaces.services.crud.ICreateService
import org.esc.tasktracker.interfaces.services.crud.IDeleteService
import org.esc.tasktracker.interfaces.services.crud.IReadService
import org.esc.tasktracker.interfaces.services.crud.IUpdateService

interface CrudService<T, ID, CrDTO, UpDTO, Filters : FilterDtoClass> :
    IReadService<T, ID, Filters>,
    ICreateService<T, ID, CrDTO>,
    IUpdateService<T, ID, UpDTO>,
    IDeleteService<T, ID>