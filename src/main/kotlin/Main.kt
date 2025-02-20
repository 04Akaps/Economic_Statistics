package org.economic.statistics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    println("Spring Boot Application Starting...")
    runApplication<Application>(*args)
    println("Spring Boot Application Started.") // 여기가 안 찍히면 문제가 있는 것
}