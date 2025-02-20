package org.economic.statistics.custom.utils

import org.economic.statistics.common.exception.CustomException
import org.economic.statistics.common.exception.ErrorCode
import org.economic.statistics.domains.PGM.service.TossPGMService
import org.economic.statistics.types.const.TOSS
import org.springframework.stereotype.Component
import org.economic.statistics.types.const.PGMList

@Component
class PGMMapper(
    tossPGMService: TossPGMService
) {
    val confirmPay: Map<String, (String) -> Unit> = PGMList.pgmList.keys.associateWith { key ->
        when (key) {
            TOSS -> { body ->  tossPGMService.confirmTossPay(body) }
            else -> throw CustomException(ErrorCode.InvalidFunctionPGMKeyMapper)
        }
    }
}
