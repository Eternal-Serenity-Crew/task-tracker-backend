package org.esc.tasktracker.interfaces

interface AbstractResponse<T> {
    val status: Int
    val message: T?
}