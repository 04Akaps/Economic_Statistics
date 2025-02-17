package org.economic.statistics.common.exception

import org.economic.statistics.common.logger.Logging
import org.economic.statistics.custom.interfaces.CodeInterface

class CustomException(
    private val codeInterface: CodeInterface,
    additionalMessage: String = ""
) : RuntimeException(
    "${codeInterface.message}${additionalMessage}"
) {
    init {
        logger.error("Exception created with code: ${codeInterface.code} and message: ${super.message}")
    }

    fun getCodeInterface(): CodeInterface {
        return this.codeInterface
    }

    companion object {
        private val logger = Logging.getLogger(CustomException::class.java)
    }
}