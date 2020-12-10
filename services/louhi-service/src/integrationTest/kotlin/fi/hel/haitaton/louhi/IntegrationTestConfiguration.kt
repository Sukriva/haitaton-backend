package fi.hel.haitaton.louhi

import io.mockk.mockk
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.JdbcOperations

@Configuration
@Profile("itest")
class IntegrationTestConfiguration {

    @Bean
    fun jdbcOperations(): JdbcOperations = mockk()
}