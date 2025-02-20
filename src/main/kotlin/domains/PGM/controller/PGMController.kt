package org.economic.statistics.domains.PGM.controller

import org.economic.statistics.custom.interfaces.PGMKeyRequest
import org.economic.statistics.custom.utils.PGMMapper
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/pgm")
class PGMController(private val mapper: PGMMapper) {


    @PostMapping("/confirm-pay/{pgm}")
    fun confirmPay(
        @PGMKeyRequest pgm: String,
        @RequestBody(required = true) jsonBody: String
    ) : Any? {
        return mapper.confirmPay[pgm.lowercase()]?.invoke(jsonBody)
    }
}