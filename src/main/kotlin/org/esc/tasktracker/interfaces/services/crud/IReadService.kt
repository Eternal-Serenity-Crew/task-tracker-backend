package org.esc.tasktracker.interfaces.services.crud

import org.esc.tasktracker.exceptions.NotFoundException
import org.esc.tasktracker.interfaces.FilterDtoClass
import org.esc.tasktracker.interfaces.services.BasicApiService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface IReadService<T, ID, Filters: FilterDtoClass> : BasicApiService<T, ID> {
    fun getAll(filters: Filters, pageable: Pageable): Page<T> = repository.findAll(pageable)
    fun getById(id: ID?, throwable: Boolean = true, message: String = "Object with id $id not found."): T? {
        if (id == null) {
            if (throwable) {
                throw NotFoundException("ID cannot be null.")
            }
            return null
        }

        val o = repository.findById(id)
        if (throwable && !o.isPresent) {
            throw NotFoundException(message)
        }
        return if (!o.isPresent) null else o.get()
    }
}