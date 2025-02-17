package org.economic.statistics.common.logger

import org.economic.statistics.common.exception.CustomException
import org.economic.statistics.common.exception.ErrorCode
import org.slf4j.Logger
import org.slf4j.LoggerFactory

// AOP를 구현하기에는, PointCut같은 부분에서 에러 발생하기 쉽기 떄문에, 원하는 부분에 적용하기 위해 싱글톤으로 구성
object Logging {
    fun <T : Any> getLogger(forClass: Class<T>): Logger = LoggerFactory.getLogger(forClass)

    fun <T> loggingStopWatch(logger: Logger, function: (MutableMap<String, Any>) -> T?): T {
        val logData = mutableMapOf<String, Any>()
        logData["startAt"] = System.currentTimeMillis()

        val result = function.invoke(logData)

        logData["endAt"] = System.currentTimeMillis()
        logger.info(logData.toString())

        result?.let {
            return it
        } ?: run {
            throw CustomException(ErrorCode.FailedToFuncInvoke, "func : $function")
        }
    }
}