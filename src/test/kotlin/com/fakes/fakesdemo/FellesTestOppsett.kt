package com.fakes.fakesdemo

import com.fakes.fakesdemo.fakes.ArbeidsforholdClientFake
import com.fakes.fakesdemo.testconfig.TestConfig
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [Application::class, ArbeidsforholdClientFake::class, TestConfig::class],
    properties = [
        "spring.main.allow-bean-definition-overriding=true",
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration",
    ],
)
class FellesTestOppsett
