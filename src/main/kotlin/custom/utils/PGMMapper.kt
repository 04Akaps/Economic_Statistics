package org.economic.statistics.custom.utils

import org.economic.statistics.common.exception.CustomException
import org.economic.statistics.common.exception.ErrorCode
import org.economic.statistics.domains.PGM.service.TossPGMService
import org.economic.statistics.types.const.TOSS
import org.springframework.stereotype.Component
import org.economic.statistics.types.const.PGMList
import org.economic.statistics.types.global.Result

@Component
class PGMMapper(
    tossPGMService: TossPGMService
) {
    private val confirmPay: Map<String, (String) -> Any> = PGMList.pgmList.keys.associateWith { key ->
        when (key) {
            TOSS -> tossPGMService::paymentRequest
            else -> throw CustomException(ErrorCode.InvalidFunctionPGMKeyMapper)
        }
    }

    fun <T> executeConfirmPay(pgm: String, body: String): T {
        val func = confirmPay[pgm.lowercase()]
            ?: throw CustomException(ErrorCode.NotSupportedPGMKeyRequest)

        return func.invoke(body) as T
    }
}
