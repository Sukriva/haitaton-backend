package fi.hel.haitaton.louhi

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application

private val logger = KotlinLogging.logger { }

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
