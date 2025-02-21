package org.economic.statistics.custom.utils

import org.economic.statistics.common.exception.CustomException
import org.economic.statistics.common.exception.ErrorCode
import org.economic.statistics.custom.interfaces.PGMService
import org.economic.statistics.types.const.TOSS
import org.springframework.stereotype.Component
import org.economic.statistics.types.const.PGMList
import org.economic.statistics.types.global.Result

@Component
class PGMMapper(
    pgmServices: List<PGMService>
) {

    // Map<String, (String) -> Result<String>>
    // 1. String : 함수 키 이름을 된다.
    // 2. (String) -> Result<String>  : 파라메터 인자와 return 값이 된다.
    private val confirmPay: Map<String, (String) -> Result<String>> = pgmServices
        .associateBy(
            keySelector = { it.getPGMKey().lowercase() },
            valueTransform = { service -> { body -> service.confirmPay(body) } }
        )

    fun executeConfirmPay(pgm: String, body: String): Result<String> {
        val lowerPgm = pgm.lowercase()

        if (!PGMList.isValidPGMType(lowerPgm)) {
            throw CustomException(ErrorCode.InvalidFunctionPGMKeyMapper)
        }


        val func = confirmPay[lowerPgm]
            ?: throw CustomException(ErrorCode.NotSupportedPGMKeyRequest)

        return func.invoke(body)
    }
}
