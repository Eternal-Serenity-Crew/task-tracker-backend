package org.esc.tasktracker.interfaces.controllers.crud

import org.esc.tasktracker.interfaces.FilterDtoClass
import org.esc.tasktracker.interfaces.controllers.BasicRestController
import org.esc.tasktracker.interfaces.services.crud.IReadService
import org.esc.tasktracker.io.BasicSuccessfulResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface IReadController<T, ID, Filters: FilterDtoClass> : BasicRestController {
    override val service: IReadService<T, ID, Filters>

    fun getAll(filters: Filters, pageable: Pageable): BasicSuccessfulResponse<Page<T>>
    fun getById(id: ID): BasicSuccessfulResponse<T>
}