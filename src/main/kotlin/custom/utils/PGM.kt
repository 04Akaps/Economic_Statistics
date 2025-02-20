package org.economic.statistics.custom.utils

import org.economic.statistics.common.exception.CustomException
import org.economic.statistics.common.exception.ErrorCode
import org.economic.statistics.domains.PGM.service.PGMService
import org.economic.statistics.types.const.TOSS
import org.springframework.stereotype.Component
import org.economic.statistics.types.const.PGMList

@Component
class PGMList(paymentService: PGMService) {
    private val pgmFunctionMap: Map<String, () -> Unit> = PGMList.pgmList.keys.associateWith { key ->
        when (key) {
            TOSS -> { -> println("Toss Incomi") }
            else -> { -> throw CustomException(ErrorCode.InvalidFunctionPGMKeyMapper) }
        }
    }
}
