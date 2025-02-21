package org.economic.statistics.custom.interfaces

import org.economic.statistics.types.global.Result

interface PGMService {
    fun confirmPay(requestBody: String) : Result<String>
}