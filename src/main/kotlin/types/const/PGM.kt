package org.economic.statistics.types.const

object PGMList{
    val pgmList = mapOf(
        TOSS to true
    )

    fun isValidPGMType(t: String): Boolean {
        pgmList[t.lowercase()]?.let { return it } ?: run { return false }
    }
}

const val TOSS = "toss"