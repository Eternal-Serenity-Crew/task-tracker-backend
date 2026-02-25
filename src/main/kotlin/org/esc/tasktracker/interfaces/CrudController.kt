package org.esc.tasktracker.interfaces

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Base interface for CRUD controllers.
 *
 * Defines standard CRUD operations that all REST controllers should implement,
 * ensuring consistent API structure across different entity types.
 *
 * @param T The entity type being managed.
 * @param ID The type of the entity's identifier.
 * @param CrDTO The DTO type for create operations.
 * @param UpDTO The DTO type for update operations.
 * @param Filters The DTO type for filtering requests.
 *
 * @see CrudService
 * @see FilterDtoClass
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
interface CrudController<T : Any, ID : Any, CrDTO : Any, UpDTO : Any, Filters : FilterDtoClass> {

    fun getAll(filters: Filters, pageable: Pageable): Page<T>
    fun getById(id: ID): T

    fun create(item: CrDTO): Any

    fun update(item: UpDTO): Any

    fun deleteAll(): Any
    fun deleteById(id : ID): Any
}