package org.economic.statistics.common.httpClient

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Component

@Component
class HttpClient(private val httpClient: OkHttpClient) {

    fun <T> GET(uri: String, headers: Map<String, String> = emptyMap(), serializer: KSerializer<T>): T? {
        val requestBuilder = Request.Builder().url(uri)

        // 헤더 추가
        headers.forEach { (key, value) -> requestBuilder.addHeader(key, value) }
        val request = requestBuilder.build()

        val response = httpClient.newCall(request).execute()

        if (!response.isSuccessful) {
            return null
        }

        val responseBody = response.body?.string() ?: return null

        // JSON 디코딩
        return try {
            JsonUtil.decodeFromJson(responseBody,serializer)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}


object JsonUtil {
    private val json = Json { prettyPrint = true }

    // 객체 -> JSON 문자열 변환
    fun <T> encodeToJson(data: T, serializer: KSerializer<T>): String {
        return json.encodeToString(serializer, data)
    }

    // JSON 문자열 -> 객체 변환
    fun <T> decodeFromJson(jsonString: String, serializer: KSerializer<T>): T {
        return json.decodeFromString(serializer, jsonString)
    }
}