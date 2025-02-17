package org.economic.statistics.common.exception

import org.economic.statistics.custom.interfaces.CodeInterface

enum class ErrorCode(
    override val code : Int,
    override val message : String
) : CodeInterface {
    BodyIsNull(-1, "response body is null"),
    FailedToGetCall(0, "Failed to get call"),
    FailedToPostCall(1, "Failed to post call");
}