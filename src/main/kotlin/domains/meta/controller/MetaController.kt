package org.economic.statistics.domains.meta.controller

import org.economic.statistics.domains.meta.service.MetaService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/meta")
class MetaController(private val metaService: MetaService) {

}