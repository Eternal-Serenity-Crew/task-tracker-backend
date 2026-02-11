package org.esc.tasktracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EscTaskTrackerApplication

fun main(args: Array<String>) {
    runApplication<EscTaskTrackerApplication>(*args)
}
