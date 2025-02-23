package org.economic.statistics.domains.PGM.service

import org.economic.statistics.custom.interfaces.PGMService
import org.economic.statistics.types.const.TEST
import org.economic.statistics.types.global.GlobalResponse
import org.springframework.stereotype.Service
import org.economic.statistics.types.global.Result

@Service
class TestPGMService() : PGMService {

    override fun confirmPay(req: String) : Result<String> {
        return GlobalResponse.success("success")
    }

    override fun requestPay(requestBody: String) : Result<String> {
        return GlobalResponse.success("success")
    }

    override fun getPGMKey() : String = TEST
}