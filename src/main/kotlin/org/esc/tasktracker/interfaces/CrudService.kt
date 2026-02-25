package org.esc.tasktracker.interfaces

import org.esc.tasktracker.exceptions.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Base interface for CRUD services.
 *
 * @param T The entity type being managed.
 * @param ID The type of the entity's identifier.
 * @param CrDTO The DTO type for create operations.
 * @param UpDTO The DTO type for update operations.
 * @param Filters The DTO type for filtering requests.
 *
 * @property repository The JPA repository for the entity type.
 *
 * @see CrudController
 * @see FilterDtoClass
 * @see NotFoundException
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
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