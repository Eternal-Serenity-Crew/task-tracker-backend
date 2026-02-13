package org.esc.tasktracker.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "config.cors")
class CorsProperties {
    lateinit var frontendLocalHost: String
    lateinit var frontendDockerHost: String
    lateinit var frontendProductionHost: String
}