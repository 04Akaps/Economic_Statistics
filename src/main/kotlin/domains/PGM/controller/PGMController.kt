package org.economic.statistics.domains.PGM.controller

import org.economic.statistics.custom.interfaces.PGMKeyRequest
import org.economic.statistics.custom.utils.PGMMapper
import org.economic.statistics.types.global.Result
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/pgm")
class PGMController(private val mapper: PGMMapper) {


    @PostMapping("/confirm-pay/{pgm}")
    fun confirmPay(
        @PathVariable(name = "pgm") pgm : String,
        @RequestBody(required = true) jsonBody: String
    ) : Result<String> {
        return mapper.executeConfirmPay(pgm, jsonBody)
    }

    @PostMapping("/payments/{pgm}")
    fun requestPay(
        @PathVariable(name = "pgm") pgm : String,
        @RequestBody(required = true) jsonBody: String
    ) : Result<String> {
        return mapper.executePayment(pgm, jsonBody)
    }

}