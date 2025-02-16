package org.economic.statistics.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAPIConfig {

    @Value("\${openapi.auth-key}")
    lateinit var _authKey: String

    @Bean
    fun authKey() = _authKey
}