package org.esc.tasktracker.interfaces.services

import org.springframework.data.jpa.repository.JpaRepository

interface BasicApiService<T, ID> {
    val repository: JpaRepository<T, ID>
}