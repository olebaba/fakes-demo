package com.fakes.fakesdemo.testconfig

import com.fakes.fakesdemo.fakes.ArbeidsforholdClientFake
import com.fakes.fakesdemo.fakes.SykmeldingRepositoryFake
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestConfig {
    @Bean
    fun arbeidsforholdClient(): ArbeidsforholdClientFake = ArbeidsforholdClientFake()

    @Bean
    fun sykmeldingRepository(): SykmeldingRepositoryFake = SykmeldingRepositoryFake()
}
