package fi.hel.haitaton.louhi

import org.apache.camel.CamelContext
import org.apache.camel.test.spring.junit5.CamelSpringBootTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@CamelSpringBootTest
@SpringBootTest(properties = ["haitaton.louhi.fromUri=timer://louhiTimer?repeatCount=1"])
internal class LouhiRouteITest {

    @Autowired
    lateinit var camelContext: CamelContext

    @Test
    fun test() {
        // TODO what?
    }
}