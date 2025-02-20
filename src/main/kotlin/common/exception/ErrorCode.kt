package org.economic.statistics.common.exception

import org.economic.statistics.custom.interfaces.CodeInterface

enum class ErrorCode(
    override val code : Int,
    override var message : String
) : CodeInterface {
    FailedToGetCall(-101, "Failed to get call"),
    FailedToPostCall(-100, "Failed to post call"),



    FailedToFuncInvoke(-2, "Failed to invoke function"),
    BodyIsNull(-1, "response body is null"),
    NotSupportedPGMKeyRequest(1, "Not Supported PGM Key Request"),
    PGMKeyRequired(-1, "PGM key required"),


    NoLanguageData(2, "no data in api call"),
    FailedToObjectMapping(3, "Failed to object mapping"),
    InvalidFunctionPGMKeyMapper(4, "Invalid function PGM key mapper"),
    FailedToParsingJsonObject(5, "Failed to parsing json object")
}