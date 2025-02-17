package org.economic.statistics.domains.statistics.controller

import org.economic.statistics.domains.statistics.service.StaticsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/statistics")
class StatisticsController(private val staticsService: StaticsService) {

    @GetMapping("/list-of-statistics")
    fun listOfStatics(
        @RequestParam(name = "page", required = true) page: Long,
        @RequestParam(name = "size", required = true) size: Long,
        @RequestParam(name = "code", required = false) statisticalCode: String?,
    ) {
        staticsService.listOfStatics(page, size, statisticalCode)
    }

    @GetMapping("/statistics-detail")
    fun statisticsDetail(
        @RequestParam(name = "page", required = true) page: Long,
        @RequestParam(name = "size", required = true) size: Long,
        @RequestParam(name = "code", required = true) statisticalCode: String,
    ) {
        staticsService.statisticsDetail(page, size, statisticalCode)
    }


    @GetMapping("/top-100-statistics")
    fun top100Statistics(
        @RequestParam(name = "page", required = true) page: Long,
        @RequestParam(name = "size", required = true) size: Long,
    ) {
        staticsService.top100Statistics(page, size)
    }

    @GetMapping("/statistics-meta")
    fun statisticsMeta(
        @RequestParam(name = "page", required = true) page: Long,
        @RequestParam(name = "size", required = true) size: Long,
        @RequestParam(name = "data", required = true) data: String,
    ) {
        staticsService.statisticsMeta(page, size, data)
    }
}