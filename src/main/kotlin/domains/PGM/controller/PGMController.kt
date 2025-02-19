package org.economic.statistics.domains.PGM.controller

import org.economic.statistics.domains.PGM.service.PGMService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/pgm")
class PGMController(private val pgmService: PGMService) {

}