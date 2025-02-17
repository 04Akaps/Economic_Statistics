package org.economic.statistics.domains.language.controller

import org.economic.statistics.domains.language.service.LanguageService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.economic.statistics.domains.language.model.SuccessAPIResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.economic.statistics.types.global.Result

// https://ecos.bok.or.kr/api/#/DevGuide/DevSpeciflcation
@RestController
@RequestMapping("/api/v1/language")
class LanguageController (private val languageService: LanguageService) {

    @GetMapping("/dictionary")
    fun statisticsLanguageDictionary(
        @RequestParam(value = "page", required = true) page: Long,
        @RequestParam(value = "size", required = true) size: Long,
        @RequestParam(value = "lang", required = true) lang: String
    )  : Result<SuccessAPIResult> {
       return languageService.statisticsLanguageDictionary(lang,page,size)
    }

}