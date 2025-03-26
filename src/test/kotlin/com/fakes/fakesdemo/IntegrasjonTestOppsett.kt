package com.fakes.fakesdemo

import com.fakes.fakesdemo.testconfig.KafkaTestConfig
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.kafka.KafkaContainer
import org.testcontainers.utility.DockerImageName

private class PostgreSQLContainer14 : PostgreSQLContainer<PostgreSQLContainer14>("postgres:14-alpine")

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(
    classes = [
        Application::class, KafkaTestConfig::class,
    ],
)
@AutoConfigureMockMvc(print = MockMvcPrint.NONE, printOnlyOnFailure = false)
class IntegrasjonTestOppsett {
    @Autowired
    lateinit var mockMvc: MockMvc

    companion object {
        init {
            KafkaContainer(DockerImageName.parse("apache/kafka-native:3.8.1")).apply {
                start()
                System.setProperty("KAFKA_BROKERS", bootstrapServers)

                println(
                    "Started kafka testcontainer: bootstrapServers=${this.bootstrapServers}, " +
                        "image=${this.dockerImageName}, containerId=${this.containerId.take(12)}",
                )
            }

            PostgreSQLContainer14().apply {
                withCommand("postgres", "-c", "wal_level=logical")
                start()
                System.setProperty("spring.datasource.url", "$jdbcUrl&reWriteBatchedInserts=true")
                System.setProperty("spring.datasource.username", username)
                System.setProperty("spring.datasource.password", password)

                println(
                    "Started Postgres testcontainer: jdbcUrl=${this.jdbcUrl}, " +
                        "image=${this.dockerImageName}, containerId=${this.containerId.take(12)}",
                )
            }
        }
    }
}
