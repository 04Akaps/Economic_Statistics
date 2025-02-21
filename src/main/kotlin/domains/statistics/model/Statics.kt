package org.economic.statistics.domains.statistics.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatisticTableList(
    @SerialName("list_total_count")
    val listTotalCount: Int,

    @SerialName("rows")
    val row: List<StatisticRow>
)

@Serializable
data class StatisticRow(
    @SerialName("P_STAT_CODE")
    val pStateCode: String,

    @SerialName("STAT_CODE")
    val stateCode: String,

    @SerialName("STAT_NAME")
    val statName: String,

    @SerialName("CYCLE")
    val cycle: String? = null,

    @SerialName("SRCH_YN")
    val srchTn: String,

    @SerialName("ORG_NAME")
    val orgName: String? = null
)
