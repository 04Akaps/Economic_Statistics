package org.economic.statistics.domains.language.service


import org.economic.statistics.common.builder.OpenAPIPathBuilder
import org.economic.statistics.common.httpClient.Client
import org.economic.statistics.common.logger.Logging
import org.economic.statistics.types.const.OpenAPIPaths
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class LanguageService (
    private val client: Client,
    private val openAPIPathBuilder: OpenAPIPathBuilder
) {
//    : String? = Logging.loggingStopWatch<String>(logger)
    fun statisticsLanguageDictionary(
        lang: String,
        page : Long,
        size : Long
    )  {
        val uri: String = openAPIPathBuilder.buildURL(OpenAPIPaths.STATISTIC_WORD, page, size, lang)

        val result : String = client.GET(uri, emptyMap())




        println("여기를 타버린다") // 응답을 받은 후 실행됨
    }

    companion object {
        private val logger: Logger = Logging.getLogger(LanguageService::class.java)
    }
}