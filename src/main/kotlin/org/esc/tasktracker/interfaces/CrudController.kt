package org.esc.tasktracker.interfaces

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CrudController<T : Any, ID : Any, CrDTO : Any, UpDTO : Any, Filters : FilterDtoClass> {

    fun getAll(filters: Filters, pageable: Pageable): Page<T>
    fun getById(id: ID): T

    fun create(item: CrDTO): Any

    fun update(item: UpDTO): Any

    fun deleteAll(): Any
    fun deleteById(id : ID): Any
}