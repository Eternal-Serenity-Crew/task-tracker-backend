package org.esc.tasktracker.interfaces

import org.esc.tasktracker.exceptions.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CrudService<T : Any, ID : Any, CrDTO : Any, UpDTO : Any, Filters : FilterDtoClass> {
    val repository: JpaRepository<T, ID>

    fun getAll(filters: Filters?, pageable: Pageable): Page<T>
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

    fun create(item: CrDTO): Any

    fun update(item: UpDTO): Any

    fun deleteById(id: ID): Any?
    fun deleteAll(): Any? = repository.deleteAll()
}