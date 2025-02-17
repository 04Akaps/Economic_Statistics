package org.economic.statistics.common.exception

import org.economic.statistics.custom.interfaces.CodeInterface

enum class ErrorCode(
    override val code : Int,
    override val message : String
) : CodeInterface {
    FailedToFuncInvoke(-2, "Failed to invoke function"),
    BodyIsNull(-1, "response body is null"),
    FailedToGetCall(0, "Failed to get call"),
    FailedToPostCall(1, "Failed to post call"),

    NoLanguageData(2, "no data in api call"),
    FailedToObjectMapping(3, "Failed to object mapping")
}