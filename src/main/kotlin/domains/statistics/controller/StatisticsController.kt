package org.economic.statistics.domains.statistics.controller

import org.economic.statistics.domains.statistics.service.StaticsService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/statistics")
class StatisticsController(private val staticsService: StaticsService) {

}