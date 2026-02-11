package org.esc.tasktracker.config

import org.esc.tasktracker.config.properties.DatabaseProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

@Configuration
class DatabaseConfig(private val dbProperties: DatabaseProperties) {

    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()

        dataSource.url = dbProperties.url
        dataSource.username = dbProperties.username
        dataSource.password = dbProperties.password
        dataSource.schema = dbProperties.schema

        return dataSource
    }
}