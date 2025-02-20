package org.economic.statistics.domains.PGM.controller

import org.economic.statistics.custom.interfaces.PGMKeyRequest
import org.economic.statistics.domains.PGM.service.PGMService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/pgm")
class PGMController(private val pgmService: PGMService) {


    @PostMapping("/confirm-pay/{pgm}")
    fun confirmPay(
        @PGMKeyRequest pgm: String,
        @RequestBody(required = true) jsonBody: String
    ) {
        // 다양한 PGM을 받아야 하기 때문에 url 경로를 맵핑
        println("Received pgm: $pgm")
        println("Request Body: $jsonBody")
    }

}