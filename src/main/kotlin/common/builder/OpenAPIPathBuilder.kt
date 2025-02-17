package org.economic.statistics.common.builder

import org.springframework.stereotype.Component

@Component
class OpenAPIPathBuilder(
    private val authKey: String,
) {
    fun buildURL(endPoint: String, page: Long, size: Long, args: String? = null): String {
        val uri  = "$endPoint/${authKey}/json/kr/${page}/${size}"

        return when (args) {
            null -> uri
            else -> "${uri}/${args}"
        }
    }
}