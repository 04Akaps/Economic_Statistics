package org.economic.statistics.types.const

const val TEST = "test"
const val TOSS = "toss"

object TossPGMPath {
    private const val BASE_URL = "https://api.tosspayments.com/v1/"

    const val CONFIRM_URL = "${BASE_URL}payments/confirm"
    const val REQUEST_URL = "${BASE_URL}payments"
}