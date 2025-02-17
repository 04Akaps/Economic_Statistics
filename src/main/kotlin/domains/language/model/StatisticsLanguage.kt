package org.economic.statistics.domains.language.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FailedAPIResult(
    @SerialName("RESULT")
    val result: FailedResponse
)

@Serializable
data class FailedResponse(
    @SerialName("CODE")
    val code: String,

    @SerialName("MESSAGE")
    val message: String
)


data class StatisticsLanguageSuccess(
    @SerialName("testval")
    val testval : String
)