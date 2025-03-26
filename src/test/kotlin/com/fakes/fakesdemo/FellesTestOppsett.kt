package com.fakes.fakesdemo

import com.fakes.fakesdemo.testconfig.TestConfig
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(
    classes = [Application::class, TestConfig::class],
    properties = [
        "spring.main.allow-bean-definition-overriding=true",
        "spring.data.jdbc.repositories.enabled=false",
        "spring.autoconfigure.exclude=" +
            "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration, " +
            "org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration",
        "KAFKA_BROKERS=$IGNORED_KAFKA_BROKERS",
    ],
)
class FellesTestOppsett

const val IGNORED_KAFKA_BROKERS = "localhost:9092"
