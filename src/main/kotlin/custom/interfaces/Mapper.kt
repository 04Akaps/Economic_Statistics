package org.economic.statistics.custom.interfaces

interface Mapper<T, V> {
    fun ToAnotherType(input: T): V
}