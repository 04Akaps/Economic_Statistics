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

@Serializable
data class SuccessAPIResult(
    @SerialName("StatisticWord")
    val statisticWord: StatisticWordData
)

@Serializable
data class StatisticWordData(
    @SerialName("list_total_count")
    val listTotalCount: Int,

    @SerialName("row")
    val row: List<WordDetail>
)

@Serializable
data class WordDetail(
    @SerialName("WORD")
    val word: String,

    @SerialName("CONTENT")
    val content: String
)


data class StatisticsLanguageResponse(
    @SerialName("message")
    val message : String,

    @SerialName("word")
    val word : String
)