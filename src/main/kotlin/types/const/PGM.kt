package org.economic.statistics.types.const

object PGMList{
    private  val pgmList = mapOf(
        "toss" to true
    )

    fun isValidPGMType(t: String): Boolean {
        pgmList[t.lowercase()]?.let { return it } ?: run { return false }
    }
}