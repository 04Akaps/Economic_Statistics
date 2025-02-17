package org.economic.statistics.common.httpClient

import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Component

@Component
class Client(private val httpClient: OkHttpClient) {

    fun GET(uri: String, headers: Map<String, String> = emptyMap()): String {
        val requestBuilder = Request.Builder().url(uri)

        headers.forEach { (key, value) -> requestBuilder.addHeader(key, value) }
        val request = requestBuilder.build()

        val response = httpClient.newCall(request).execute()


        // TODO Custom Exception
        if (!response.isSuccessful) {
            throw Exception(response.message)
        }

        response.body?.let {
            return it.string()
        } ?: run {
            throw Exception(response.message)
        }
    }
}