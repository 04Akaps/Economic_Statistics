package org.economic.statistics.custom.utils

import org.economic.statistics.common.exception.CustomException
import org.economic.statistics.common.exception.ErrorCode
import org.economic.statistics.custom.interfaces.PGMService
import org.economic.statistics.types.const.TOSS
import org.springframework.stereotype.Component
import org.economic.statistics.types.const.PGMList

@Component
class PGMMapper(
    pgmService: PGMService
) {

    private val confirmPay: Map<String, (String) -> Any> = PGMList.pgmList.keys.associateWith { pgmService::confirmPay }

    fun <T> executeConfirmPay(pgm: String, body: String): T {
        val lowerPgm = pgm.lowercase()

        if (!PGMList.isValidPGMType(lowerPgm)) {
            throw CustomException(ErrorCode.InvalidFunctionPGMKeyMapper)
        }

        val func = confirmPay[lowerPgm]
            ?: throw CustomException(ErrorCode.NotSupportedPGMKeyRequest)

        return func.invoke(body) as T
    }
}
