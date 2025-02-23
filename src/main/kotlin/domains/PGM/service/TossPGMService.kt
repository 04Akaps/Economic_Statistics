package org.economic.statistics.domains.PGM.service

import kotlinx.serialization.json.JsonObject
import org.economic.statistics.common.config.TossPGM
import org.economic.statistics.common.exception.CustomException
import org.economic.statistics.common.exception.ErrorCode
import org.economic.statistics.common.httpClient.Client
import org.economic.statistics.common.json.JsonUtil
import org.economic.statistics.common.logger.Logging
import org.economic.statistics.custom.interfaces.PGMService
import org.economic.statistics.domains.PGM.model.ConfirmRequestMapper
import org.economic.statistics.domains.PGM.types.ConfirmBody
import org.economic.statistics.domains.PGM.types.PayRequest
import org.economic.statistics.domains.PGM.types.PaymentRequest
import org.economic.statistics.types.const.TOSS
import org.economic.statistics.types.const.TossPGMPath
import org.economic.statistics.types.global.GlobalResponse
import org.economic.statistics.types.global.Result
import org.slf4j.Logger
import org.springframework.stereotype.Service
import java.util.Base64
import java.nio.charset.StandardCharsets

@Service
class TossPGMService(
    private val client: Client,
    private val config : TossPGM,
) : PGMService {

    override fun confirmPay(requestBody: String) : Result<String> = Logging.loggingStopWatch(logger) { log ->
        log["request"] = requestBody

        val request: PaymentRequest

        try {
            request = JsonUtil.decodeFromJson(requestBody, PaymentRequest.serializer())
        } catch (e : Exception) {
            val msg  = " body : ${requestBody}, msg : ${e.message}"
            throw CustomException(ErrorCode.FailedToParsingJsonObject, msg)
        }

//        val body :  ConfirmBody = ConfirmRequestMapper.ToAnotherType(request)
//        val requestBody = JsonUtil.encodeToJson(body, ConfirmBody.serializer())

        val secretKey = config.secretKey
        val encodedBytes = Base64.getEncoder().encode(("$secretKey:").toByteArray(StandardCharsets.UTF_8))
        val basicKey = String(encodedBytes, StandardCharsets.UTF_8)

        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to "Basic $basicKey"
        )

        client.POST(TossPGMPath.CONFIRM_URL, headers, request.toString())

        // TODO -> 성공 케이스에 대해서 히스토리 기록
        // 결제 confirm 요청이기 떄문에, 성공 유무만 노출
        return@loggingStopWatch GlobalResponse.success("SUCCESS")
    }


    override fun requestPay(requestBody: String) : Result<String> = Logging.loggingStopWatch(logger) { log ->
        // Redis Cache 적용 및 Lua Script 작업 --> 데이터 영속성 처리
        val request: PayRequest

        try {
            request = JsonUtil.decodeFromJson(requestBody, PayRequest.serializer())
        } catch (e : Exception) {
            val msg  = " body : ${requestBody}, msg : ${e.message}"
            throw CustomException(ErrorCode.FailedToParsingJsonObject, msg)
        }

        val secretKey = config.secretKey
        val encodedBytes = Base64.getEncoder().encode(("$secretKey:").toByteArray(StandardCharsets.UTF_8))
        val basicKey = String(encodedBytes, StandardCharsets.UTF_8)

        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to "Basic $basicKey"
        )

        client.POST(TossPGMPath.REQUEST_URL, headers, request.toString())

        // TODO -> 성공 케이스에 대해서 히스토리 기록
        // TODO chagne return response
        return@loggingStopWatch GlobalResponse.success("SUCCESS")
    }

    override fun getPGMKey() : String = TOSS

    companion object {
        private val logger: Logger = Logging.getLogger(TossPGMService::class.java)
    }
}