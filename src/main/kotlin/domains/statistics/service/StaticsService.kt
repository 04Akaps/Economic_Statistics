package org.economic.statistics.domains.statistics.service

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import org.economic.statistics.common.builder.OpenAPIPathBuilder
import org.economic.statistics.common.exception.CustomException
import org.economic.statistics.common.exception.ErrorCode
import org.economic.statistics.common.httpClient.Client
import org.economic.statistics.common.json.JsonUtil
import org.economic.statistics.common.logger.Logging
import org.economic.statistics.domains.language.service.LanguageService
import org.economic.statistics.types.const.OpenAPIPaths
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class StaticsService(
    private val client: Client,
    private val openAPIPathBuilder: OpenAPIPathBuilder
) {

    fun listOfStatics(page: Long, size: Long, statisticalCode: String? = null) {
        val uri: String = openAPIPathBuilder.buildURL(OpenAPIPaths.STATISTIC_TABLE_LIST, page, size, statisticalCode)

        val apiResponse : String = client.GET(uri, emptyMap())
        val element : JsonElement = JsonUtil.parseToJsonElement(apiResponse)


        // open API인데.. API 규격이 다르기 떄문에 element를 확인
        if (element is JsonObject) {

        } else {
            throw CustomException(ErrorCode.FailedToObjectMapping)
        }
    }

    fun statisticsDetail(page: Long, size: Long, statisticalCode: String) {
        val uri: String = openAPIPathBuilder.buildURL(OpenAPIPaths.STATISTIC_ITEM_LIST, page, size, statisticalCode)

        val apiResponse : String = client.GET(uri, emptyMap())
        val element : JsonElement = JsonUtil.parseToJsonElement(apiResponse)

        // open API인데.. API 규격이 다르기 떄문에 element를 확인
        if (element is JsonObject) {

        } else {
            throw CustomException(ErrorCode.FailedToObjectMapping)
        }
    }

    fun top100Statistics(page: Long, size: Long) {
        val uri: String = openAPIPathBuilder.buildURL(OpenAPIPaths.STATISTIC_KEY_LIST, page, size)

        val apiResponse : String = client.GET(uri, emptyMap())
        val element : JsonElement = JsonUtil.parseToJsonElement(apiResponse)

        // open API인데.. API 규격이 다르기 떄문에 element를 확인
        if (element is JsonObject) {

        } else {
            throw CustomException(ErrorCode.FailedToObjectMapping)
        }
    }

    fun statisticsMeta(page: Long, size: Long, data: String) {
        val uri: String = openAPIPathBuilder.buildURL(OpenAPIPaths.STATISTIC_META, page, size)

        val apiResponse : String = client.GET(uri, emptyMap())
        val element : JsonElement = JsonUtil.parseToJsonElement(apiResponse)

        // open API인데.. API 규격이 다르기 떄문에 element를 확인
        if (element is JsonObject) {

        } else {
            throw CustomException(ErrorCode.FailedToObjectMapping)
        }
    }

    companion object {
        private val logger: Logger = Logging.getLogger(StaticsService::class.java)
    }

}