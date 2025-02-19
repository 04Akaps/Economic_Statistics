package org.economic.statistics.types.const

object OpenAPIPaths {
    private const val BASE_URL = "https://ecos.bok.or.kr/api/"

    const val STATISTIC_TABLE_LIST = "${BASE_URL}StatisticTableList"
    const val STATISTIC_WORD = "${BASE_URL}StatisticWord"
    const val STATISTIC_ITEM_LIST  = "${BASE_URL}StatisticItemList"
    const val STATISTIC_KEY_LIST = "${BASE_URL}KeyStatisticList"
    const val STATISTIC_META = "${BASE_URL}StatisticMeta"
}