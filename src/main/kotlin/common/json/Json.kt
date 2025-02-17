package org.economic.statistics.common.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

object JsonUtil {
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    // 객체 -> JSON 문자열 변환
    fun <T> encodeToJson(data: T, serializer: KSerializer<T>): String {
        return json.encodeToString(serializer, data)
    }

    // JSON 문자열 -> 객체 변환
    fun <T> decodeFromJson(jsonString: String, serializer: KSerializer<T>): T {
        return json.decodeFromString(serializer, jsonString)
    }

    fun parseToJsonElement(jsonString: String) = json.parseToJsonElement(jsonString)
}