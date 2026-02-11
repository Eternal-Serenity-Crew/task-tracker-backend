package org.esc.tasktracker.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "database")
class DatabaseProperties {
    lateinit var url: String
    lateinit var username: String
    lateinit var password: String
    lateinit var schema: String
}