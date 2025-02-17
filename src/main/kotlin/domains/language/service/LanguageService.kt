package org.economic.statistics.domains.language.service


import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.serializer
import org.economic.statistics.common.builder.OpenAPIPathBuilder
import org.economic.statistics.common.httpClient.Client
import org.economic.statistics.common.json.JsonUtil
import org.economic.statistics.common.logger.Logging
import org.economic.statistics.domains.language.model.FailedAPIResult
import org.economic.statistics.domains.language.model.StatisticsLanguageSuccess
import org.economic.statistics.types.const.OpenAPIPaths
import org.economic.statistics.types.global.GlobalResponse
import org.economic.statistics.types.global.Result
import org.slf4j.Logger
import org.springframework.stereotype.Service



@Service
class LanguageService (
    private val client: Client,
    private val openAPIPathBuilder: OpenAPIPathBuilder
) {

    fun statisticsLanguageDictionary(
        lang: String,
        page : Long,
        size : Long
    ) : Result<StatisticsLanguageSuccess> = Logging.loggingStopWatch(logger) { request ->
        request["lang"] = lang
        request["page"] = page
        request["size"] = size

        val uri: String = openAPIPathBuilder.buildURL(OpenAPIPaths.STATISTIC_WORD, page, size, lang)
        val apiResponse : String = client.GET(uri, emptyMap())
        val element : JsonElement = JsonUtil.parseToJsonElement(apiResponse)

        //    element.jsonObject

        //        val data : FailedAPIResult = JsonUtil.decodeFromJson(result, serializer<FailedAPIResult>())

        //        println(data)

        val response : Result<StatisticsLanguageSuccess> = GlobalResponse.success(StatisticsLanguageSuccess("resr"))

        return@loggingStopWatch response
    }

    companion object {
        private val logger: Logger = Logging.getLogger(LanguageService::class.java)
    }
}