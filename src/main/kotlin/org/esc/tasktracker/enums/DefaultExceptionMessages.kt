package org.esc.tasktracker.enums

enum class DefaultExceptionMessages(private val message: String) {
    USER_NOT_FOUND("Пользователь с таким ID не найден."),
    TEAM_NOT_FOUND("Команда с таким ID не найдена."),
    TEAM_MEMBERSHIP_NOT_FOUND("Записи об участии с таким набором параметров не найдено.");

    companion object {
        fun DefaultExceptionMessages.getMessage() = this.message
    }
}