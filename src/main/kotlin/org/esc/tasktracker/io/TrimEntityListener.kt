package org.esc.tasktracker.io

import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import org.esc.tasktracker.annotations.NotTrimmable
import java.lang.reflect.Field
import kotlin.reflect.KClass

class TrimEntityListener {
    private val stringFieldsCache = mutableMapOf<KClass<*>, List<Field>>()

    @PrePersist
    @PreUpdate
    fun trimStrings(entity: Any) {
        val entityClass = entity::class
        val fields = stringFieldsCache.getOrPut(entityClass) {
            entityClass.java.declaredFields
                .filter { it.type == String::class.java }
                .filter { it.getAnnotation(NotTrimmable::class.java) == null }
                .also { fields ->
                    fields.forEach { it.isAccessible = true }
                }
        }

        fields.forEach { field ->
            (field[entity] as? String)?.takeIf { it.isNotBlank() }?.let { trimmed ->
                field[entity] = trimmed.trim()
            }
        }
    }
}