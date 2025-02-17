package org.economic.statistics.domains.language.service

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import org.economic.statistics.common.builder.OpenAPIPathBuilder
import org.economic.statistics.common.exception.CustomException
import org.economic.statistics.common.exception.ErrorCode
import org.economic.statistics.common.httpClient.Client
import org.economic.statistics.common.json.JsonUtil
import org.economic.statistics.common.logger.Logging
import org.economic.statistics.domains.language.model.FailedAPIResult
import org.economic.statistics.domains.language.model.SuccessAPIResult
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
    ) : Result<SuccessAPIResult> = Logging.loggingStopWatch(logger) { log ->
        log["lang"] = lang
        log["page"] = page
        log["size"] = size

        val uri: String = openAPIPathBuilder.buildURL(OpenAPIPaths.STATISTIC_WORD, page, size, lang)

        val apiResponse : String = client.GET(uri, emptyMap())
        val element : JsonElement = JsonUtil.parseToJsonElement(apiResponse)

        // open API인데.. API 규격이 다르기 떄문에 element를 확인
        if (element is JsonObject) {
            when {
                "StatisticWord" in element -> {
                    val response : SuccessAPIResult = JsonUtil.decodeFromJson(apiResponse, SuccessAPIResult.serializer())
                    return@loggingStopWatch GlobalResponse.success(response)
                }
                "RESULT" in element -> {
                    val response : FailedAPIResult = JsonUtil.decodeFromJson(apiResponse, FailedAPIResult.serializer())
                    throw CustomException(ErrorCode.NoLanguageData, response.result.message)
                }
                else -> {
                    throw CustomException(ErrorCode.NoLanguageData)
                }
            }
        } else {
            throw CustomException(ErrorCode.FailedToObjectMapping)
        }
    }

    companion object {
        private val logger: Logger = Logging.getLogger(LanguageService::class.java)
    }
}