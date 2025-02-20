package org.economic.statistics.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class TossPGM () {

    @Value("\${pgm.toss.client-key}")
    lateinit var clientKey: String

    @Value("\${pgm.toss.secret-key}")
    lateinit var secretKey: String

    @Value("\${pgm.toss.security-key}")
    lateinit var securityKey: String
}