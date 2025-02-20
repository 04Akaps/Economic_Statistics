package org.economic.statistics.domains.PGM.service

import org.economic.statistics.common.config.TossPGM
import org.economic.statistics.common.exception.CustomException
import org.economic.statistics.common.exception.ErrorCode
import org.economic.statistics.common.httpClient.Client
import org.economic.statistics.common.json.JsonUtil
import org.economic.statistics.domains.PGM.types.PaymentRequest
import org.springframework.stereotype.Service

@Service
class TossPGMService(
    private val client: Client,
    private val config : TossPGM,
) {
    fun paymentRequest(requestBody: String) {
        val request: PaymentRequest

        try {
            request = JsonUtil.decodeFromJson(requestBody, PaymentRequest.serializer())
        } catch (e : Exception) {
            throw CustomException(ErrorCode.FailedToParsingJsonObject, requestBody)
        }


        // TODO -> POST request

        //        val url: URL = URL("https://api.tosspayments.com/v1/payments/confirm")
        //        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        //        connection.setRequestProperty("Authorization", authorizations)
        //        connection.setRequestProperty("Content-Type", "application/json")
        //        connection.setRequestMethod("POST")
        //        connection.setDoOutput(true)


//        client.POST()


//        val apiResponse : String = client.GET(uri, emptyMap())




    }
}