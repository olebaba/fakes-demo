package com.fakes.fakesdemo.testconfig

import com.fakes.fakesdemo.fakes.TjenesteClientFake
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestConfig {
    @Bean
    fun tjenesteClient(): TjenesteClientFake = TjenesteClientFake()
}
