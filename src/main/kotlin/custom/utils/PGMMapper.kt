package org.economic.statistics.custom.utils

import org.economic.statistics.common.exception.CustomException
import org.economic.statistics.common.exception.ErrorCode
import org.economic.statistics.custom.interfaces.PGMService
import org.economic.statistics.types.const.TOSS
import org.springframework.stereotype.Component
import org.economic.statistics.types.const.PGMList

@Component
class PGMMapper(
    pgmServices: List<PGMService>
) {

    private val confirmPay: Map<String, (String) -> Any> = pgmServices
        .filter { PGMList.isValidPGMType(it.getPGMKey()) }
        .associateBy(
            keySelector = { it.getPGMKey().lowercase() },
            valueTransform = { it::confirmPay }
        )

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
